// sagas/reviewSaga.js
import { call, put, takeLatest } from "redux-saga/effects";
import axios from "../../api/axios";

import {
  fetchReviewsRequest, fetchReviewsSuccess, fetchReviewsFailure,
  searchReviewsRequest, searchReviewsSuccess, searchReviewsFailure,
  setCondition,
  fetchReviewFormRequest, fetchReviewFormSuccess, fetchReviewFormFailure,
  reviewPolishRequest, reviewPolishSuccess, reviewPolishFailure,
  createReviewRequest, createReviewSuccess, createReviewFailure,
  updateReviewRequest, updateReviewSuccess, updateReviewFailure,
  deleteReviewRequest, deleteReviewSuccess, deleteReviewFailure,
  fetchModalReviewsRequest, fetchModalReviewsSuccess, fetchModalReviewsFailure, closeModalReviews,
  openReviewDetail, closeReviewDetail,
  resetReviewFlags,
} from "../../reducers/review/reviewReducer";

    // 목록
    // payload: { pageNo, condition }
    function* fetchReviews(action) {
      try {
        const { pageNo = 1, condition = "" } = action.payload || {};
    
        const { data } = yield call(() => axios.get("/reviewboard/reviewPaging", {
            params: {
              pageNo, ...(condition ? { condition } : {}),
            },
          })
        );
    
        yield put(
          fetchReviewsSuccess({ ...data, pageNo,}));
      } catch (err) {
        yield put(fetchReviewsFailure(err.response?.data?.message || err.message));
      }
    }

    // 검색 + 페이징 
    // payload: { keyword, searchType, pageNo, condition }
    function* searchReviews(action) {
    try {
        const {
        keyword = "",
        searchType = "all",
        pageNo = 1,
        condition = "",
        } = action.payload || {};

        const { data } = yield call(() =>
        axios.get("/foodboard/foodsearch", {
            params: {
            keyword,
            searchType,
            pageNo,
            ...(condition ? { condition } : {}),
            },
        })
        );

        yield put(
        searchReviewsSuccess({...data, pageNo,}));
    } catch (err) {
        yield put(searchReviewsFailure(err.response?.data?.message || err.message));
    }
    }

    // 입력/수정용 폼 데이터
       // payload: { reviewid? }
    function* fetchReviewForm(action) {
    try {
        const { reviewid } = action.payload || {};

        const { data } = yield call(() =>
        axios.get("/reviewboard/form", {
            params: reviewid ? { reviewid } : {},
        })
        );

        yield put(fetchReviewFormSuccess(data));
    } catch (err) {
        yield put(fetchReviewFormFailure(err.response?.data?.message || err.message));
    }
    }


    // 문장 순화 API
    // payload: { title, reviewcomment }
    function reviewPolishApi(data) {  //action 아님
    return axios.post("/reviewboard/reviewapi", null, {
        params: data,
    });
    }
    export function* reviewPolish(action) {
    try {
        const { title, reviewcomment } = action.payload || {};

        const response = yield call(reviewPolishApi, { title, reviewcomment });
        // response.data: String
        yield put(reviewPolishSuccess(response.data));
    } catch (err) {
        yield put(reviewPolishFailure(err.response?.data?.message || err.message));
    }
    }

