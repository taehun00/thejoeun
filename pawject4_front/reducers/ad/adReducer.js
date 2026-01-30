import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  ads: [],
  currentAd: null,
  loading: false,
  error: null,
};

const adSlice = createSlice({
  name: 'ad',
  initialState,
  reducers: {
    fetchAdRequest: (state, action) => {
      state.loading = true;
      state.error = null;
    },
    fetchAdSuccess: (state, action) => {
      state.loading = false;
      state.currentAd = action.payload;
    },
    fetchAdFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
      state.currentAd = null;
    },

    createAdRequest: (state, action) => {
      state.loading = true;
      state.error = null;
    },
    createAdSuccess: (state, action) => {
      state.loading = false;
      state.ads.unshift(action.payload);
    },
    createAdFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    updateAdRequest: (state, action) => {
      state.loading = true;
      state.error = null;
    },
    updateAdSuccess: (state, action) => {
      state.loading = false;
      state.ads = state.ads.map((ad) =>
        ad.id === action.payload.id ? action.payload : ad
      );
      state.currentAd = action.payload;
    },
    updateAdFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    deleteAdRequest: (state, action) => {
      state.loading = true;
      state.error = null;
    },
    deleteAdSuccess: (state, action) => {
      state.loading = false;
      state.ads = state.ads.filter((ad) => ad.id !== action.payload);
      if (state.currentAd && state.currentAd.id === action.payload) {
        state.currentAd = null;
      }
    },
    deleteAdFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    
    clearAdError: (state) => {
      state.error = null;
    }
  },
});

export const {
  fetchAdRequest, fetchAdSuccess, fetchAdFailure,
  createAdRequest, createAdSuccess, createAdFailure,
  updateAdRequest, updateAdSuccess, updateAdFailure,
  deleteAdRequest, deleteAdSuccess, deleteAdFailure,
  clearAdError
} = adSlice.actions;

export default adSlice.reducer;