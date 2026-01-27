// api/axios.js
import axios from "axios";

const api = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:8484",
  withCredentials: true,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});

// ✅ 요청 인터셉터: AccessToken 헤더 자동 첨부
api.interceptors.request.use(
  (config) => {
    if (typeof window !== "undefined") {
      const accessToken = localStorage.getItem("accessToken");
      if (accessToken) {
        config.headers.Authorization = `Bearer ${accessToken}`;
      }
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// ✅ 응답 인터셉터: 401이면 refresh 후 원요청 재시도
api.interceptors.response.use(
  (res) => res,
  async (error) => {
    const original = error.config;
    const status = error.response?.status;

    // 원래 요청 정보가 없으면 그대로 throw
    if (!original) return Promise.reject(error);

    // ✅ refresh API 호출 자체가 401이면 재시도 금지 (무한루프 방지 핵심)
    if (original.url?.includes("/api/users/refresh")) {
      return Promise.reject(error);
    }

    // ✅ 401 & 아직 재시도 안했을 때만 refresh
    if (status === 401 && !original._retry) {
      original._retry = true;

      try {
        const { data } = await api.post("/api/users/refresh");
        const newAccessToken = data?.accessToken;

        if (typeof window !== "undefined" && newAccessToken) {
          localStorage.setItem("accessToken", newAccessToken);
        }

        // 원 요청에 새 토큰 세팅
        original.headers = original.headers || {};
        original.headers.Authorization = `Bearer ${newAccessToken}`;

        return api(original);
      } catch (refreshErr) {
        if (typeof window !== "undefined") {
          localStorage.removeItem("accessToken");
          window.location.href = "/login";
        }
        return Promise.reject(refreshErr);
      }
    }

    return Promise.reject(error);
  }
);

export default api;
