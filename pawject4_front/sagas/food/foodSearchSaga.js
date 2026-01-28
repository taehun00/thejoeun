// sagas/food/foodSearchSaga.js
import { call, put, takeLatest, select } from "redux-saga/effects";
import axios from "../../api/axios";

import {
  fetchInitRequest,
  fetchInitSuccess,
  fetchInitFailure,

  searchFilterPagingRequest,
  searchFilterPagingSuccess,
  searchFilterPagingFailure,

  foodApiRequest,
  foodApiSuccess,
  foodApiFailure,

  openModal,
  fetchModalCardRequest,
  fetchModalCardSuccess,
  fetchModalCardFailure,

  setPstartno,
} from "../../reducers/food/foodSearchReducer";


// init
function initApi() {
  return axios.get("/petfoodsearcher/init");
}

// search + paging
function searchFilterPagingApi(params) {
  return axios.get("/petfoodsearcher/searchfilterPaging", { params });
}

// ai filter
function foodApiApi(userMessage) {
  const fd = new URLSearchParams();
  fd.append("userMessage", userMessage);

  return axios.post("/petfoodsearcher/foodapi", fd, {
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
  });
}

// modal card
function modalCardApi(foodid) {
  return axios.get(`/petfoodsearcher/modalcard/${foodid}`);
}

/**
 * 공통 유틸: 쿼리 파라미터 정리
 * - null/undefined/"" 인 값은 key 자체를 삭제
 * - axios 직렬화 이슈 방지
 */
function cleanParams(obj) {
  const out = { ...(obj || {}) };

  Object.keys(out).forEach((k) => {
    const v = out[k];

    if (typeof v === "string" && v.trim() === "") {
      delete out[k];
      return;
    }

    if (v === null || v === undefined) {
      delete out[k];
      return;
    }
  });

  return out;
}


// init
function* fetchInit() {
  try {
    const { data } = yield call(initApi);
    yield put(fetchInitSuccess(data));
  } catch (e) {
    yield put(fetchInitFailure(e?.response?.data || e.message));
  }
}

// 검색+페이징
function* searchFilterPaging(action) {
  try {
    const payload = action.payload || {};

    const stateFilters = yield select((state) => state.search?.filters || {});
    const statePstartno = yield select((state) => state.search?.pstartno || 1);

    const merged = {
      ...stateFilters,
      ...(payload.filters || {}),
    };

  const nextPstartno =
    payload.pstartno !== undefined && payload.pstartno !== null
      ? payload.pstartno : (statePstartno || 1);

    // store 페이지번호 동기화
    yield put(setPstartno(nextPstartno));

    // params 정리해서 전송
    const params = cleanParams({
      keyword: merged.keyword ?? null,
      pettypeid: merged.pettypeid ?? null,
      foodtype: merged.foodtype ?? null,
      brandid: merged.brandid ?? null,
      foodid: merged.foodid ?? null,
      category: merged.category ?? null,
      petagegroup: merged.petagegroup ?? null,
      isgrainfree: merged.isgrainfree ?? null,
      origin: merged.origin ?? null,
      rangeid: merged.rangeid ?? null,
      minvalue: merged.minvalue ?? null,
      maxvalue: merged.maxvalue ?? null,
      condition: merged.condition ?? null,
      pstartno: nextPstartno,
    });

    const { data } = yield call(() => searchFilterPagingApi(params));
    yield put(searchFilterPagingSuccess(data));
  } catch (e) {
    yield put(searchFilterPagingFailure(e?.response?.data || e.message));
  }
}

// AI 필터 변환
function* foodApi(action) {
  try {
    const { userMessage } = action.payload || {};
    const { data } = yield call(() => foodApiApi(userMessage));
    yield put(foodApiSuccess(data));
  } catch (e) {
    yield put(foodApiFailure(e?.response?.data || e.message));
  }
}

// 모달 상세 오픈 - 상세카드 호출
function* openModalAndFetch(action) {
  try {
    const foodid = action.payload;
    if (!foodid) return;

    yield put(fetchModalCardRequest());

    const { data } = yield call(() => modalCardApi(foodid));

    //  레이스 가드 - 응답 꼬임 방지
    const modal = yield select((state) => state.search?.modal);
    if (!modal?.open) return;
    if (modal?.foodid !== foodid) return;

    yield put(fetchModalCardSuccess(data));
  } catch (e) {
    yield put(fetchModalCardFailure(e?.response?.data || e.message));
  }
}


export default function* foodSearchSaga() {
  yield takeLatest(fetchInitRequest.type, fetchInit);
  yield takeLatest(searchFilterPagingRequest.type, searchFilterPaging);
  yield takeLatest(foodApiRequest.type, foodApi);
  yield takeLatest(openModal.type, openModalAndFetch);
}
