    // pages/petfoodsearch/index.js
import { useEffect, useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";

import { Alert, Spin, Row, Col } from "antd";
import BoardCard from "../../components/common/BoardCard";

// 부품 4개
import PetfoodSearchFilters from "../../components/petfoodsearch/PetfoodSearchFilters";
import PetfoodSearchAiBox from "../../components/petfoodsearch/PetfoodSearchAiBox";
import PetfoodSearchResultList from "../../components/petfoodsearch/PetfoodSearchResultList";
import PetfoodDetailModal from "../../components/petfoodsearch/PetfoodDetailModal";

// reducer actions 
import {
  fetchInitRequest,
  searchFilterPagingRequest,
  setFilters,
  setPstartno,

  foodApiRequest,

  openModal,
  closeModal,
} from "../../reducers/food/foodSearchReducer";

export default function PetfoodSearchPage() {
  const dispatch = useDispatch();

  const {
    // init
    initData,
    initLoading,
    initError,

    // search result
    list,
    total,
    paging,
    loading,
    error,

    // filters
    filters,
    pstartno,

    // ai
    ai,

    // modal
    modal,
  } = useSelector((state) => state.search);

  //초기 로드
  useEffect(() => {
    dispatch(fetchInitRequest());
  }, [dispatch]);

  /**
   *  검색 실행
   * - 기존 타임리프: searchPetfood() / doPetfoodSearch()
   * - 리액트: dispatch로만 통일
   */
  const runSearch = useCallback(
    ({ nextFilters, nextPstartno } = {}) => {
      // 필터 변경 시 store 반영
      if (nextFilters) {
        dispatch(setFilters(nextFilters));
      }

      // 페이지 변경 시 store 반영
      if (typeof nextPstartno === "number") {
        dispatch(setPstartno(nextPstartno));
      }

      dispatch(
        searchFilterPagingRequest({
          filters: nextFilters || null,
          pstartno: typeof nextPstartno === "number" ? nextPstartno : null,
        })
      );
    },
    [dispatch]
  );

// 필터 변경
  const onChangeFilters = useCallback(
    (patch) => {
      dispatch(setFilters(patch));
    },
    [dispatch]
  );

    //검색 버튼
  const onClickSearch = useCallback(() => {

    const hasAnyValue = Object.entries(filters || {}).some(([k, v]) => {
      if (k === "condition") return false; // 정렬은 검색조건 아님
      if (v === null || v === undefined) return false;
      if (typeof v === "string" && v.trim() === "") return false;
      return true;
    });

    if (!hasAnyValue) {
      alert("검색 조건을 하나 이상 선택해 주세요.");
      return;
    }

    runSearch({ nextPstartno: 1 });
  }, [filters, runSearch]);

//정렬 변경
  const onChangeCondition = useCallback(
    (condition) => {
      dispatch(setFilters({ condition: condition || null }));
      runSearch({ nextPstartno: 1, nextFilters: { condition: condition || null } });
    },
    [dispatch, runSearch]
  );

 //페이지 이동
  const onChangePage = useCallback(
    (page) => {
      runSearch({ nextPstartno: page });
    },
    [runSearch]
  );

//상세 모달
  const onOpenModal = useCallback(
    (foodid) => {
      dispatch(openModal(foodid));
    },
    [dispatch]
  );

  const onCloseModal = useCallback(() => {
    dispatch(closeModal());
  }, [dispatch]);

//ai 필터 요청
  const onAskAi = useCallback(
    (userMessage) => {
      dispatch(foodApiRequest({ userMessage }));
    },
    [dispatch]
  );

//결과 적용
  const onApplyAiFilters = useCallback(
    (filterPatch) => {
      if (!filterPatch) return;
      dispatch(setFilters(filterPatch));
    },
    [dispatch]
  );

////
  return (
    <BoardCard title="사료 찾기">
      {/* init  */}
      {initLoading && (
        <div style={{ padding: 24, textAlign: "center" }}>
          <Spin />
        </div>
      )}

      {initError && (
        <Alert
          type="error"
          showIcon
          message="초기값 로드 실패"
          description={String(initError)}
          style={{ marginBottom: 12 }}
        />
      )}

      {/* filters + ai */}
      <Row gutter={[12, 12]}>
        <Col xs={24} lg={16}>
          <PetfoodSearchFilters
            initData={initData}
            filters={filters}
            onChangeFilters={onChangeFilters}
            onClickSearch={onClickSearch}
          />
        </Col>

        <Col xs={24} lg={8}>
          <PetfoodSearchAiBox
            loading={ai?.loading}
            result={ai?.result}
            error={ai?.error}
            open={ai?.open}
            onAsk={onAskAi}
            onApplyAiFilters={onApplyAiFilters}
          />
        </Col>
      </Row>

      {/* result */}
      <div style={{ marginTop: 16 }}>
        {error && (
          <Alert
            type="error"
            showIcon
            message="검색 실패"
            description={String(error)}
            style={{ marginBottom: 12 }}
          />
        )}

        <PetfoodSearchResultList
          list={list}
          total={total}
          paging={paging}
          loading={loading}
          filters={filters}
          pstartno={pstartno}
          onChangeCondition={onChangeCondition}
          onChangePage={onChangePage}
          onOpenModal={onOpenModal}
        />
      </div>

      {/* modal */}
      <PetfoodDetailModal
        open={modal?.open}
        loading={modal?.loading}
        dto={modal?.dto}
        error={modal?.error}
        onClose={onCloseModal}
      />
    </BoardCard>
  );
}
