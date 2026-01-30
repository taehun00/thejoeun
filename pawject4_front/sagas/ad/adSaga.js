// sagas/adSaga.js

import { call, put, takeLatest } from 'redux-saga/effects';
import api from '../../api/axios';
import {
  fetchAdRequest, fetchAdSuccess, fetchAdFailure,
  createAdRequest, createAdSuccess, createAdFailure,
  updateAdRequest, updateAdSuccess, updateAdFailure,
  deleteAdRequest, deleteAdSuccess, deleteAdFailure,
} from "../../reducers/ad/adReducer";

// 1. 광고 단건 조회 (수정 폼에 데이터 채우기용)
export function* fetchAd(action) {
  try {
    const response = yield call(api.get, `/ads/${action.payload.adId}`);
    yield put(fetchAdSuccess(response.data));
  } catch (e) {
    yield put(fetchAdFailure(e.response?.data?.message || e.message));
  }
}

// 2. 광고 등록 (FormData 활용)
export function* createAd(action) {
  try {
    const { dto, file } = action.payload;
    const formData = new FormData();
    formData.append('dto', new Blob([JSON.stringify(dto)], { type: 'application/json' }));
    if (file) formData.append('file', file);

    // AS-IS: '/ads/ads' -> TO-BE: '/ads' (일반적인 경로)
    const response = yield call(api.post, '/ads', formData, {
      headers: { "Content-Type": "multipart/form-data" }
    });
    yield put(createAdSuccess(response.data));
  } catch (e) {
    yield put(createAdFailure(e.response?.data?.message || e.message));
  }
}

// 3. 광고 수정
export function* updateAd(action) {
  try {
    const { adId, dto, file } = action.payload;
    const formData = new FormData();
    formData.append('dto', new Blob([JSON.stringify(dto)], { type: 'application/json' }));
    if (file) formData.append('file', file);

    // AS-IS: `/ads/${adId}/ads` -> TO-BE: `/ads/${adId}`
    const response = yield call(api.put, `/ads/${adId}`, formData, {
      headers: { "Content-Type": "multipart/form-data" }
    });
    yield put(updateAdSuccess(response.data));
  } catch (e) {
    yield put(updateAdFailure(e.response?.data?.message || e.message));
  }
}

// 4. 광고 삭제
export function* deleteAd(action) {
  try {
    yield call(api.delete, `/ads/${action.payload.adId}`);
    yield put(deleteAdSuccess(action.payload.adId));
    alert('광고가 삭제되었습니다.');
  } catch (e) {
    yield put(deleteAdFailure(e.response?.data?.message || e.message));
  }
}

export default function* adSaga() {
  yield takeLatest(fetchAdRequest.type, fetchAd);
  yield takeLatest(createAdRequest.type, createAd);
  yield takeLatest(updateAdRequest.type, updateAd);
  yield takeLatest(deleteAdRequest.type, deleteAd);
}