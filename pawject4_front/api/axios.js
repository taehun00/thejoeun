// api/axios.js
import axios from "axios";

const api = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:8484",
  withCredentials: true,
  headers: {
    Accept: "application/json",
    // ❌ Content-Type을 여기서 고정하지 않는다
  },
});

// 요청 인터셉터
api.interceptors.request.use(
  (config) => {
    // ✅ Access Token 추가
    if (typeof window !== "undefined") {
      const accessToken = localStorage.getItem("accessToken");
      if (accessToken) {
        config.headers.Authorization = `Bearer ${accessToken}`;
      }
    }

    // ✅ FormData면 Content-Type 제거 → axios가 multipart 자동 설정
    if (config.data instanceof FormData) {
      delete config.headers["Content-Type"];
    } else {
      // ✅ 일반 JSON 요청만 Content-Type 지정
      config.headers["Content-Type"] = "application/json";
    }

    return config;
  },
  (error) => Promise.reject(error)
);

// 응답 인터셉터
api.interceptors.response.use(
  (res) => res,
  async (error) => {
    const original = error.config;
    const status = error.response?.status;

    // 회원가입 / 로그인 / refresh는 재시도 안 함
    if (
      original.url.includes("/user/signup") ||
      original.url.includes("/user/login") ||
      original.url.includes("/refresh")
    ) {
      return Promise.reject(error);
    }

    // 401 → refresh token 시도
    if (status === 401 && !original._retry) {
      original._retry = true;
      try {
        const { data } = await api.post("/api/users/refresh");
        const newAccessToken = data?.accessToken;

        if (typeof window !== "undefined" && newAccessToken) {
          localStorage.setItem("accessToken", newAccessToken);
        }

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
