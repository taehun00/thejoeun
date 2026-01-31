import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  reports: [],
  page: 0,
  size: 10,
  totalPages: 0,
  totalElements: 0,
  loading: false,
  error: null,
};

const adminReportSlice = createSlice({
  name: "adminReport",
  initialState,
  reducers: {
    // 신고 목록 조회
    fetchReportsRequest: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchReportsSuccess: (state, action) => {
      state.loading = false;
      state.reports = action.payload.content;
      state.page = action.payload.pageable.pageNumber;
      state.size = action.payload.size;
      state.totalPages = action.payload.totalPages;
      state.totalElements = action.payload.totalElements;
    },
    fetchReportsFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    // 신고 처리
    handleReportRequest: (state) => {
      state.loading = true;
      state.error = null;
    },
    handleReportSuccess: (state) => {
      state.loading = false;
    },
    handleReportFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
  },
});

export const {
  fetchReportsRequest,
  fetchReportsSuccess,
  fetchReportsFailure,
  handleReportRequest,
  handleReportSuccess,
  handleReportFailure,
} = adminReportSlice.actions;

export default adminReportSlice.reducer;
