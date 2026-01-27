import { call, put, takeLatest } from "redux-saga/effects";
import { message } from "antd";
import Cookies from "js-cookie";
import api from "../../api/axios";
import Router from "next/router";
import {
  signupRequest, signupSuccess, signupFailure,
  loginRequest, loginSuccess, loginFailure,
  refreshTokenRequest, refreshTokenSuccess, refreshTokenFailure,
  logoutRequest, logout, logoutFailure,
  updateNicknameRequest, updateNicknameSuccess, updateNicknameFailure,
  updateProfileImageRequest, updateProfileImageSuccess, updateProfileImageFailure,
} from "../../reducers/user/authReducer";

/* =========================
   회원가입 API
========================= */
function signupApi(formData) {
  return api.post("/api/users/signup", formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
}

export function* signup(action) {
  try {
    yield call(signupApi, action.payload);
    yield put(signupSuccess());
    message.success("회원가입 성공!");
    Router.push("/login");
  } catch (err) {
    yield put(signupFailure(err.response?.data?.error || err.message));
    message.error("회원가입 실패");
  }
}

/* =========================
   로그인 API
========================= */
function loginApi(payload) {
  return api.post("/api/users/login", payload);
}

export function* login(action) {
  try {
    const { data } = yield call(loginApi, action.payload);
    const { accessToken, refreshToken, ...user } = data;

    if (user && accessToken) {
      if (typeof window !== "undefined") {
        localStorage.setItem("accessToken", accessToken);
        Cookies.set("refreshToken", refreshToken);
      }
      yield put(loginSuccess({ user, accessToken }));
      message.success(`${user.nickname}님 환영합니다!`);
      Router.push("/mypage");
    } else {
      yield put(loginFailure("로그인 실패"));
      message.error("아이디/비밀번호를 확인하세요.");
    }
  } catch (err) {
    yield put(loginFailure(err.response?.data?.error || err.message));
    message.error("로그인 실패");
  }
}

/* =========================
   토큰 재발급 API
========================= */
function refreshApi() {
  return api.post("/api/users/refresh"); // refreshToken은 HttpOnly 쿠키에서 자동 포함
}

export function* refresh(action) {
  try {
    const { data } = yield call(refreshApi);
    const newAccessToken = data?.accessToken;

    if (typeof window !== "undefined" && newAccessToken) {
      localStorage.setItem("accessToken", newAccessToken);
    }

    yield put(refreshTokenSuccess({ accessToken: newAccessToken }));
  } catch (err) {
    yield put(refreshTokenFailure(err.response?.data?.error || err.message));
    yield put(logout());
  }
}


/* =========================
   로그아웃 API
========================= */
function logoutApi(email) {
  return api.delete(`/api/users?email=${email}`);
}

export function* logoutFlow(action) {
  try {
    yield call(logoutApi, action.payload.email);
    if (typeof window !== "undefined") {
      localStorage.removeItem("accessToken");
      Cookies.remove("refreshToken");
    }
    yield put(logout());
    message.success("로그아웃 완료");
    Router.push("/login");
  } catch (err) {
    yield put(logoutFailure(err.response?.data?.error || err.message));
  }
}

/* =========================
   닉네임 변경 API
========================= */
function updateNicknameApi({ userId, nickname }) {
  return api.patch(`/api/users/${userId}/nickname`, null, {
    params: { nickname },
  });
}

export function* updateNickname(action) {
  try {
    const { data } = yield call(updateNicknameApi, action.payload);
    yield put(updateNicknameSuccess({ user: data }));
    message.success("닉네임 변경 완료");
  } catch (err) {
    yield put(updateNicknameFailure(err.response?.data?.error || err.message));
    message.error("닉네임 변경 실패");
  }
}

/* =========================
   프로필 이미지 변경 API
========================= */
function updateProfileImageApi({ userId, file }) {
  const formData = new FormData();
  formData.append("ufile", file);

  return api.post(`/api/users/${userId}/profile-image`, formData, {
    headers: { "Content-Type": "multipart/form-data" },
  });
}

export function* updateProfileImage(action) {
  try {
    const { data } = yield call(updateProfileImageApi, action.payload);
    yield put(updateProfileImageSuccess({ user: data }));
    message.success("프로필 이미지 변경 완료");
  } catch (err) {
    yield put(updateProfileImageFailure(err.response?.data?.error || err.message));
    message.error("프로필 이미지 변경 실패");
  }
}


/* =========================
   Root Saga
========================= */
export default function* authSaga() {
  yield takeLatest(signupRequest.type, signup);
  yield takeLatest(loginRequest.type, login);
  yield takeLatest(refreshTokenRequest.type, refresh);
  yield takeLatest(logoutRequest.type, logoutFlow);
  yield takeLatest(updateNicknameRequest.type, updateNickname);
  yield takeLatest(updateProfileImageRequest.type, updateProfileImage);
}
