import React, { useEffect, useState } from "react";
import MainPage from "../components/MainPage";
import api from "../api/axios"; // axios 설정
import { fetchActiveAdsRequest } from "../reducers/ad/adReducer"; // 액션 임포트

export default function MainPagePage() {
  const [reviewList, setReviewList] = useState([]);
  const [smartList, setSmartList] = useState([]);
  const [diseaseList, setDiseaseList] = useState([]);
  // 리덕스에서 광고 목록 가져오기
  //const { activeAds, setActiveAds } = useSelector((state) => state.ad);

  useEffect(() => {
    async function fetchData() {
      try {
        const [reviews, smart, disease, /*activeAds*/] = await Promise.all([
          api.get("/reviews/latest"),
          api.get("/smart/latest"),
          api.get("/disease/latest"),
          //api.get("/activeAds/latest")

        ]);
        setReviewList(reviews.data);
        setSmartList(smart.data);
        setDiseaseList(disease.data);
        //setActiveAds(activeAds.data);
      } catch (e) {
        console.error("MainPage 데이터 fetch 실패", e);
      }
    }
    fetchData();
  }, []);

  return <MainPage reviewList={reviewList} smartList={smartList} diseaseList={diseaseList} />;
}
