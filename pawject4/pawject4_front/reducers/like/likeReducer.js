import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  reviewLikes: {},   // { reviewId: count }
  testerLikes: {},   // { testerId: count }
  loading: false,
  error: null,
  success: false,
};

const likesSlice = createSlice({
  name: "likes",
  initialState,
  reducers: {
    // --- 리뷰 좋아요 ---
    likeReviewRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = false;
    },
    likeReviewSuccess: (state, action) => {
      state.loading = false;
      state.success = true;
      const { reviewId, count } = action.payload;
      state.reviewLikes[reviewId] = count;
    },
    likeReviewFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    // --- 체험단 좋아요 ---
    likeTesterRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = false;
    },
    likeTesterSuccess: (state, action) => {
      state.loading = false;
      state.success = true;
      const { testerId, count } = action.payload;
      state.testerLikes[testerId] = count;
    },
    likeTesterFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    // --- 리뷰 좋아요 취소 ---
    removeLikeReviewRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = false;
    },
    removeLikeReviewSuccess: (state, action) => {
      state.loading = false;
      state.success = true;
      const { reviewId, count } = action.payload;
      state.reviewLikes[reviewId] = count;
    },
    removeLikeReviewFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    // --- 체험단 좋아요 취소 ---
    removeLikeTesterRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = false;
    },
    removeLikeTesterSuccess: (state, action) => {
      state.loading = false;
      state.success = true;
      const { testerId, count } = action.payload;
      state.testerLikes[testerId] = count;
    },
    removeLikeTesterFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    // --- 좋아요 수 조회 ---
    countLikesReviewRequest: (state) => {
      state.loading = true;
      state.error = null;
    },
    countLikesReviewSuccess: (state, action) => {
      state.loading = false;
      const { reviewId, count } = action.payload;
      state.reviewLikes[reviewId] = count;
    },
    countLikesReviewFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    countLikesTesterRequest: (state) => {
      state.loading = true;
      state.error = null;
    },
    countLikesTesterSuccess: (state, action) => {
      state.loading = false;
      const { testerId, count } = action.payload;
      state.testerLikes[testerId] = count;
    },
    countLikesTesterFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
  },
});

export const {
  likeReviewRequest,
  likeReviewSuccess,
  likeReviewFailure,
  likeTesterRequest,
  likeTesterSuccess,
  likeTesterFailure,
  removeLikeReviewRequest,
  removeLikeReviewSuccess,
  removeLikeReviewFailure,
  removeLikeTesterRequest,
  removeLikeTesterSuccess,
  removeLikeTesterFailure,
  countLikesReviewRequest,
  countLikesReviewSuccess,
  countLikesReviewFailure,
  countLikesTesterRequest,
  countLikesTesterSuccess,
  countLikesTesterFailure,
} = likesSlice.actions;

export default likesSlice.reducer;
