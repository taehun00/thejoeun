// sagas/petfoodsearch/petfoodSearchSaga.js
import { all, call, put, takeLatest, select } from "redux-saga/effects";
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
} from "../../reducers/food/foodSearchReducer";

/**
 * API
 */

// 1) init
function initApi() {
  return axios.get("/petfoodsearcher/init");
}

// 2) search + paging
function searchFilterPagingApi(params) {
  return axios.get("/petfoodsearcher/searchfilterPaging", { params });
}

// 3) ai filter
function foodApiApi(userMessage) {
  // 컨트롤러: @RequestParam String userMessage
  // => x-www-form-urlencoded 또는 params로 보내면 됨
  const fd = new URLSearchParams();
  fd.append("userMessage", userMessage);

  return axios.post("/petfoodsearcher/foodapi", fd, {
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
  });
}

// 4) modal card
function modalCardApi(foodid) {
  return axios.get(`/petfoodsearcher/modalcard/${foodid}`);
}

/**
 * SAGA workers
 */

// init
function* fetchInit() {
  try {
    const { data } = yield call(initApi);
    // data: SearchPetfoodInitResponse
    yield put(fetchInitSuccess(data));
  } catch (e) {
    yield put(fetchInitFailure(e?.response?.data || e.message));
  }
}

// 검색+페이징
// payload: { filters?, pstartno?, condition? }
function* searchFilterPaging(action) {
  try {
    const payload = action.payload || {};

    // store에서 filters 가져오기
    const stateFilters = yield select((state) => state.petfoodSearch?.filters || {});
    const statePstartno = yield select((state) => state.petfoodSearch?.pstartno || 1);

    const merged = {
      ...stateFilters,
      ...(payload.filters || {}),
    };

    // 컨트롤러 파라미터 이름 맞춤
    const params = {
      keyword: merged.keyword || null,
      pettypeid: merged.pettypeid || null,
      foodtype: merged.foodtype || null,
      brandid: merged.brandid || null,
      foodid: merged.foodid || null,
      category: merged.category || null,
      petagegroup: merged.petagegroup || null,
      isgrainfree: merged.isgrainfree || null,
      origin: merged.origin || null,
      rangeid: merged.rangeid || null,
      minvalue: merged.minvalue ?? null,
      maxvalue: merged.maxvalue ?? null,

      condition: merged.condition || null, // 정렬
      pstartno: payload.pstartno || statePstartno || 1,
    };

    // null/"" 정리 (서버 단에서 required=false니까 null로 보내도 됨)
    Object.keys(params).forEach((k) => {
      if (params[k] === "" || params[k] === undefined) params[k] = null;
    });

    const { data } = yield call(() => searchFilterPagingApi(params));
    // data: { list, total, paging }

    yield put(searchFilterPagingSuccess(data));
  } catch (e) {
    yield put(searchFilterPagingFailure(e?.response?.data || e.message));
  }
}

// AI 필터 변환
// payload: { userMessage }
function* foodApi(action) {
  try {
    const { userMessage } = action.payload || {};
    const { data } = yield call(() => foodApiApi(userMessage));
    yield put(foodApiSuccess(data));
  } catch (e) {
    yield put(foodApiFailure(e?.response?.data || e.message));
  }
}

// 모달 상세 오픈 => 상세카드 호출
function* openModalAndFetch(action) {
  try {
    const foodid = action.payload;
    if (!foodid) return;

    yield put(fetchModalCardRequest());
    const { data } = yield call(() => modalCardApi(foodid));
    yield put(fetchModalCardSuccess(data));
  } catch (e) {
    yield put(fetchModalCardFailure(e?.response?.data || e.message));
  }
}

/**
 * watchers
 */
function* watchFetchInit() {
  yield takeLatest(fetchInitRequest.type, fetchInit);
}

function* watchSearchFilterPaging() {
  yield takeLatest(searchFilterPagingRequest.type, searchFilterPaging);
}

function* watchFoodApi() {
  yield takeLatest(foodApiRequest.type, foodApi);
}

function* watchOpenModal() {
  yield takeLatest(openModal.type, openModalAndFetch);
}

/**
 * root saga
 */
export default function* petfoodSearchSaga() {
  yield all([
    watchFetchInit(),
    watchSearchFilterPaging(),
    watchFoodApi(),
    watchOpenModal(),
  ]);
}
