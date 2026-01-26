// reducers/foodReducer.js
import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  // 목록
  foods: [],
  total: 0,
  paging: null,
  mode: "list", // "list" | "search"
  pageNo: 1,

  // 검색/정렬
  keyword: "",
  searchType: "all",
  condition: "",

  // 상세
  detail: null, // { fdto, nutrientList }

  // 폼 데이터
  formData: null,
  // formData 구조 (컨트롤러 /foodboard/form)
  // {
  //   brandlist: [],
  //   nutrientlist: [],
  //   dto?: 수정 시 fdto,
  //   nutriList?: 수정 시 기존 영양소 리스트
  // }

  // OCR
  ocrLoading: false,
  ocrResult: "",
  ocrError: null,

  // 등록/수정
  writeLoading: false,
  writeSuccess: false,
  writeError: null,

  editLoading: false,
  editSuccess: false,
  editError: null,

  // 삭제+빠른삭제
  deleteLoading: false,
  deleteSuccess: false,
  deleteError: null,

  // 공통 
  loading: false,
  error: null,
};

const foodSlice = createSlice({
  name: "food",
  initialState,
  reducers: {
    // =========================
    // 목록
    // payload: { pageNo, condition }
    // =========================
    fetchFoodsRequest: (state, action) => {
      state.loading = true;
      state.error = null;
    },
    fetchFoodsSuccess: (state, action) => {
      state.loading = false;
      state.mode = "list";
      state.foods = action.payload?.list || [];
      state.total = action.payload?.total || 0;
      state.paging = action.payload?.paging || null;
      state.pageNo = action.payload?.pageNo || 1;
    },
    fetchFoodsFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    // =========================
    // 검색
    // payload: { keyword, searchType, pageNo, condition }
    // =========================
    searchFoodsRequest: (state, action) => {
      state.loading = true;
      state.error = null;

      state.mode = "search";
      state.keyword = action.payload?.keyword || "";
      state.searchType = action.payload?.searchType || "all";
    },
    searchFoodsSuccess: (state, action) => {
      state.loading = false;
      state.mode = "search";
      state.foods = action.payload?.list || [];
      state.total = action.payload?.total || 0;
      state.paging = action.payload?.paging || null;
      state.pageNo = action.payload?.pageNo || 1;
    },
    searchFoodsFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    // =========================
    // 정렬
    // =========================
    setCondition: (state, action) => {
      state.condition = action.payload || "";
    },

    // =========================
    // 상세
    // payload: { foodid }
    // =========================
    fetchFoodDetailRequest: (state, action) => {
      state.loading = true;
      state.error = null;
      state.detail = null;
    },
    fetchFoodDetailSuccess: (state, action) => {
      state.loading = false;
      state.detail = action.payload;
    },
    fetchFoodDetailFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
      state.detail = null;
    },

    // =========================
    // 폼 데이터 (등록/수정 공통)
    // payload: { foodid? }
    // =========================
    fetchFoodFormRequest: (state, action) => {
      state.loading = true;
      state.error = null;
      state.formData = null;
    },
    fetchFoodFormSuccess: (state, action) => {
      state.loading = false;
      state.formData = action.payload;
    },
    fetchFoodFormFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
      state.formData = null;
    },

    // =========================
    // OCR
    // payload: { file }
    // =========================
    foodOcrRequest: (state, action) => {
      state.ocrLoading = true;
      state.ocrError = null;
      state.ocrResult = "";
    },
    foodOcrSuccess: (state, action) => {
      state.ocrLoading = false;
      state.ocrResult = action.payload || "";
    },
    foodOcrFailure: (state, action) => {
      state.ocrLoading = false;
      state.ocrError = action.payload;
    },

    // =========================
    // 등록
    // payload: { dto, nutrientid[], amount[], file }
    // =========================
    createFoodRequest: (state, action) => {
      state.writeLoading = true;
      state.writeSuccess = false;
      state.writeError = null;
    },
    createFoodSuccess: (state, action) => {
      state.writeLoading = false;
      state.writeSuccess = true;
      // action.payload: { success, foodid }
    },
    createFoodFailure: (state, action) => {
      state.writeLoading = false;
      state.writeError = action.payload;
    },

    // =========================
    // 수정
    // payload: { foodid, dto, nutrientid[], amount[], file }
    // =========================
    updateFoodRequest: (state, action) => {
      state.editLoading = true;
      state.editSuccess = false;
      state.editError = null;
    },
    updateFoodSuccess: (state, action) => {
      state.editLoading = false;
      state.editSuccess = true;
    },
    updateFoodFailure: (state, action) => {
      state.editLoading = false;
      state.editError = action.payload;
    },

    // =========================
    // 삭제 (정식 삭제)
    // payload: { foodid }
    // =========================
    deleteFoodRequest: (state, action) => {
      state.deleteLoading = true;
      state.deleteSuccess = false;
      state.deleteError = null;
    },
    deleteFoodSuccess: (state, action) => {
      state.deleteLoading = false;
      state.deleteSuccess = true;

      const foodid = action.payload;
      state.foods = state.foods.filter((f) => f.foodid !== foodid);
      state.total = state.total > 0 ? state.total - 1 : 0;
    },
    deleteFoodFailure: (state, action) => {
      state.deleteLoading = false;
      state.deleteError = action.payload;
    },

    // =========================
    // 빠른삭제
    // payload: { foodid }
    // =========================
    quickDeleteFoodRequest: (state, action) => {
      state.deleteLoading = true;
      state.deleteSuccess = false;
      state.deleteError = null;
    },
    quickDeleteFoodSuccess: (state, action) => {
      state.deleteLoading = false;
      state.deleteSuccess = true;

      const foodid = action.payload;
      state.foods = state.foods.filter((f) => f.foodid !== foodid);
      state.total = state.total > 0 ? state.total - 1 : 0;
    },
    quickDeleteFoodFailure: (state, action) => {
      state.deleteLoading = false;
      state.deleteError = action.payload;
    },

    // =========================
    // 플래그 리셋 (페이지 이동 시 유용)
    // =========================
    resetFoodFlags: (state) => {
      state.writeSuccess = false;
      state.writeError = null;

      state.editSuccess = false;
      state.editError = null;

      state.deleteSuccess = false;
      state.deleteError = null;

      state.ocrError = null;
    },
  },
});

export const {
  fetchFoodsRequest,
  fetchFoodsSuccess,
  fetchFoodsFailure,

  searchFoodsRequest,
  searchFoodsSuccess,
  searchFoodsFailure,

  setCondition,

  fetchFoodDetailRequest,
  fetchFoodDetailSuccess,
  fetchFoodDetailFailure,

  fetchFoodFormRequest,
  fetchFoodFormSuccess,
  fetchFoodFormFailure,

  foodOcrRequest,
  foodOcrSuccess,
  foodOcrFailure,

  createFoodRequest,
  createFoodSuccess,
  createFoodFailure,

  updateFoodRequest,
  updateFoodSuccess,
  updateFoodFailure,

  deleteFoodRequest,
  deleteFoodSuccess,
  deleteFoodFailure,

  quickDeleteFoodRequest,
  quickDeleteFoodSuccess,
  quickDeleteFoodFailure,

  resetFoodFlags,
} = foodSlice.actions;

export default foodSlice.reducer;
