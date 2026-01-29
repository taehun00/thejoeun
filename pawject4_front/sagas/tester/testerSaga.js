// sagas/tester/testerSaga.js
import { call, put, takeLatest } from "redux-saga/effects";
import axios from "../../api/axios";

import {
  // list
  fetchTesterListRequest,
  fetchTesterListSuccess,
  fetchTesterListFailure,

  // search
  searchTesterRequest,
  searchTesterSuccess,
  searchTesterFailure,

  // detail
  fetchTesterDetailRequest,
  fetchTesterDetailSuccess,
  fetchTesterDetailFailure,

  // create
  createTesterAdminRequest,
  createTesterAdminSuccess,
  createTesterAdminFailure,

  createTesterUserRequest,
  createTesterUserSuccess,
  createTesterUserFailure,

  // update
  updateTesterRequest,
  updateTesterSuccess,
  updateTesterFailure,

  // delete
  deleteTesterRequest,
  deleteTesterSuccess,
  deleteTesterFailure,

  // toggle
  toggleTesterNoticeRequest,
  toggleTesterNoticeSuccess,
  toggleTesterNoticeFailure,

  toggleTesterStatusRequest,
  toggleTesterStatusSuccess,
  toggleTesterStatusFailure,
} from "../../reducers/tester/testerReducer";

/**
 *  API 정리
 * - GET    /tester/paged
 * - GET    /tester/search
 * - GET    /tester/{testerid}
 * - POST   /tester/admin (multipart)
 * - POST   /tester/user  (multipart)
 * - PUT    /tester/{testerid} (multipart)
 * - DELETE /tester/{testerid}
 * - PATCH  /tester/{testerid}/notice
 * - PATCH  /tester/{testerid}/status
 */

// 목록 조회 (paged)
// payload: { pageNo, condition }
function* fetchTesterList(action) {
  try {
    const { pageNo = 1, condition = "" } = action.payload || {};

    const { data } = yield call(() =>
      axios.get("/tester/paged", {
        params: {
          pageNo,
          ...(condition ? { condition } : {}),
        },
      })
    );

    yield put(fetchTesterListSuccess({ ...data, pageNo }));
  } catch (err) {
    yield put(fetchTesterListFailure(err.response?.data?.message || err.message));
  }
}

// 검색 + 페이징
// payload: { keyword, searchType, pageNo, condition }
function* searchTester(action) {
  try {
    const {
      keyword = "",
      searchType = "all",
      pageNo = 1,
      condition = "",
    } = action.payload || {};

    const { data } = yield call(() =>
      axios.get("/tester/search", {
        params: {
          keyword,
          searchType,
          pageNo,
          ...(condition ? { condition } : {}),
        },
      })
    );

    yield put(searchTesterSuccess({ ...data, pageNo }));
  } catch (err) {
    yield put(searchTesterFailure(err.response?.data?.message || err.message));
  }
}

// 상세
// payload: { testerid }
function* fetchTesterDetail(action) {
  try {
    const { testerid } = action.payload || {};
    if (!testerid) throw new Error("testerid 누락");

    const { data } = yield call(() => axios.get(`/tester/${testerid}`));

    yield put(fetchTesterDetailSuccess(data));
  } catch (err) {
    yield put(fetchTesterDetailFailure(err.response?.data?.message || err.message));
  }
}

// 관리자 글쓰기 - multipart
// payload: { dto, files?[] }
function* createTesterAdmin(action) {
  try {
    const { dto, files } = action.payload || {};
    if (!dto) throw new Error("dto 누락");

    const formData = new FormData();

    // dto
    Object.entries(dto || {}).forEach(([k, v]) => {
      if (v !== undefined && v !== null) formData.append(k, v);
    });

    // files
    if (Array.isArray(files) && files.length > 0) {
      files.forEach((f) => {
        if (f) formData.append("files", f);
      });
    }

    const { data } = yield call(() =>
      axios.post("/tester/admin", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      })
    );

    yield put(createTesterAdminSuccess(data));
  } catch (err) {
    yield put(createTesterAdminFailure(err.response?.data?.message || err.message));
  }
}

// 유저 글쓰기 - multipart
// payload: { dto, files?[] }
function* createTesterUser(action) {
  try {
    const { dto, files } = action.payload || {};
    if (!dto) throw new Error("dto 누락");

    const formData = new FormData();

    Object.entries(dto || {}).forEach(([k, v]) => {
      if (v !== undefined && v !== null) formData.append(k, v);
    });

    if (Array.isArray(files) && files.length > 0) {
      files.forEach((f) => {
        if (f) formData.append("files", f);
      });
    }

    const { data } = yield call(() =>
      axios.post("/tester/user", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      })
    );

    yield put(createTesterUserSuccess(data));
  } catch (err) {
    yield put(createTesterUserFailure(err.response?.data?.message || err.message));
  }
}

// 수정(공통) - multipart
// payload: { testerid, dto, files?[], keepImgIds?[] }
function* updateTester(action) {
  try {
    const { testerid, dto, files, keepImgIds } = action.payload || {};
    if (!testerid) throw new Error("testerid 누락");
    if (!dto) throw new Error("dto 누락");

    const formData = new FormData();

    // dto
    Object.entries(dto || {}).forEach(([k, v]) => {
      if (v !== undefined && v !== null) formData.append(k, v);
    });

    // 기존 이미지 유지
    if (Array.isArray(keepImgIds) && keepImgIds.length > 0) {
      keepImgIds.forEach((id) => {
        if (id !== undefined && id !== null) formData.append("keepImgIds", id);
      });
    }

    // files (새 이미지)
    if (Array.isArray(files) && files.length > 0) {
      files.forEach((f) => {
        if (f) formData.append("files", f);
      });
    }

    const { data } = yield call(() =>
      axios.put(`/tester/${testerid}`, formData, {
        headers: { "Content-Type": "multipart/form-data" },
      })
    );

    yield put(updateTesterSuccess(data));
  } catch (err) {
    yield put(updateTesterFailure(err.response?.data?.message || err.message));
  }
}

// 삭제
// payload: { testerid }
function* deleteTester(action) {
  try {
    const { testerid } = action.payload || {};
    if (!testerid) throw new Error("testerid 누락");

    yield call(() => axios.delete(`/tester/${testerid}`));

    yield put(deleteTesterSuccess({ testerid }));
  } catch (err) {
    yield put(deleteTesterFailure(err.response?.data?.message || err.message));
  }
}


// 공지 토글 (PATCH)
// payload: { testerid }
function* toggleTesterNotice(action) {
  try {
    const { testerid } = action.payload || {};
    if (!testerid) throw new Error("testerid 누락");

    const { data } = yield call(() => axios.patch(`/tester/${testerid}/notice`));
    // data: 0 or 1

    yield put(toggleTesterNoticeSuccess({ testerid, isnotice: data }));
  } catch (err) {
    yield put(toggleTesterNoticeFailure(err.response?.data?.message || err.message));
  }
}


// 모집상태 토글 (PATCH)
// payload: { testerid }
function* toggleTesterStatus(action) {
  try {
    const { testerid } = action.payload || {};
    if (!testerid) throw new Error("testerid 누락");

    const { data } = yield call(() => axios.patch(`/tester/${testerid}/status`));
    // data: 0 or 1

    yield put(toggleTesterStatusSuccess({ testerid, status: data }));
  } catch (err) {
    yield put(toggleTesterStatusFailure(err.response?.data?.message || err.message));
  }
}

export default function* testerSaga() {
  // list/search
  yield takeLatest(fetchTesterListRequest.type, fetchTesterList);
  yield takeLatest(searchTesterRequest.type, searchTester);

  // detail
  yield takeLatest(fetchTesterDetailRequest.type, fetchTesterDetail);

  // create
  yield takeLatest(createTesterAdminRequest.type, createTesterAdmin);
  yield takeLatest(createTesterUserRequest.type, createTesterUser);

  // update/delete
  yield takeLatest(updateTesterRequest.type, updateTester);
  yield takeLatest(deleteTesterRequest.type, deleteTester);

  // toggles
  yield takeLatest(toggleTesterNoticeRequest.type, toggleTesterNotice);
  yield takeLatest(toggleTesterStatusRequest.type, toggleTesterStatus);
}
