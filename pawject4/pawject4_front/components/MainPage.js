import React, { useState, useEffect } from "react";
import Link from "next/link";
import { Table, Button } from "antd";

const styles = {
  page: {
    fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", Arial, sans-serif',
    background: "#f2f5f8",
    color: "#333",
    minHeight: "100vh",
    padding: 20,
  },

  subArea: {
    maxWidth: 900,
    margin: "30px auto 50px",
    display: "flex",
    gap: 16,
    justifyContent: "center",
    flexWrap: "wrap",
  },

  mainActionBtn: {
    display: "inline-block",
    padding: "16px 28px",
    borderRadius: 14,
    fontSize: 16,
    fontWeight: 600,
    textAlign: "center",
    textDecoration: "none",
    color: "#fff",
    background: "#4c6fbf",
    boxShadow: "0 6px 16px rgba(0,0,0,.12)",
    transition: "all 0.2s ease",
    cursor: "pointer",
    minWidth: 140,
  },

  mainActionBtnYellow: {
    background: "#f4c430",
    color: "#2b2b2b",
  },

  logoutBtn: {
    background: "#f44336",
    color: "#fff",
  },

  mainSections: {
    maxWidth: 1200,
    margin: "0 auto 50px",
    padding: "0 20px",
    display: "grid",
    gridTemplateColumns: "repeat(3, 1fr)",
    gap: 24,
  },

  part: {
    background: "#f8fafc",
    borderRadius: 14,
    padding: 22,
    minHeight: 360,
    display: "flex",
    flexDirection: "column",
    boxShadow: "0 6px 18px rgba(0,0,0,.06)",
  },

  partTitle: {
    fontSize: 17,
    fontWeight: 700,
    marginBottom: 6,
    textAlign: "center",
  },

  partDesc: {
    fontSize: 13,
    color: "#6b7280",
    marginBottom: 14,
    textAlign: "center",
  },

  textEnd: {
    marginTop: "auto",
    textAlign: "right",
  },

  moreBtn: {
    fontSize: 12,
    padding: "5px 12px",
    background: "#4c6fbf",
    color: "#fff",
    borderRadius: 6,
    textDecoration: "none",
  },
};

// 더미데이터 넣어도 ok 아니면 pages/mainpage.js에서 api 경로 맞추기
// 더보기에 알맞은 링크 넣기
export default function MainPage({
  reviewList = [],
  smartList = [],
  diseaseList = [],
}) {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    setIsLoggedIn(!!token);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("accessToken");
    setIsLoggedIn(false);
    alert("로그아웃 되었습니다.");
  };

  // --- 더미 데이터 예시 ---
  const dummyReviews = [
    { id: 1, foodname: "도기푸드", title: "맛있어요", reviewcomment: "우리 강아지가 좋아합니다" },
    { id: 2, foodname: "캣푸드", title: "좋아요", reviewcomment: "우리 고양이가 좋아해요" },
  ];

  const dummySmart = [
    { postid: 101, etitle: "산책 팁", econtent: "매일 30분 산책하세요" },
  ];

  const dummyDisease = [
    { id: 201, disname: "심장병", disex: "주의해야 합니다", kindpet: "강아지" },
  ];

  return (
    <div style={styles.page}>
      {/* CTA 버튼 영역 */}
      <div style={styles.subArea}>
        {!isLoggedIn ? (
          <>
            <Link href="/user/login">
            <a style={styles.mainActionBtn}>로그인</a>
            </Link>
            <Link href="/user/signup">
            <a style={{ ...styles.mainActionBtn, ...styles.mainActionBtnYellow }}>회원가입</a>
            </Link>
          </>
        ) : (
          <>
            <Link href="/user/mypage">
            <a style={styles.mainActionBtn}>마이페이지</a>
            </Link>
            <a 
            onClick={handleLogout} 
            style={{ ...styles.mainActionBtn, background: "#f44336", cursor: "pointer" }}
            >
            로그아웃
            </a>
          </>
        )}
      </div>

      {/* 카드 섹션 */}
      <section style={styles.mainSections}>
        {/* 사료 리뷰 */}
        <div style={styles.part}>
          <h3 style={styles.partTitle}>사료 리뷰</h3>
          <p style={styles.partDesc}>최근 등록된 리뷰를 확인해 보세요</p>
          <Table
            size="small"
            pagination={false}
            dataSource={reviewList.length ? reviewList : dummyReviews}
            rowKey="id"
            columns={[
              { title: "사료", dataIndex: "foodname" },
              { title: "제목", dataIndex: "title" },
              { title: "내용", dataIndex: "reviewcomment" },
            ]}
          />
          <div style={styles.textEnd}>
            <Link href="/reviews" style={styles.moreBtn}>
              더보기
            </Link>
          </div>
        </div>

        {/* 운동 스마트 */}
        <div style={styles.part}>
          <h3 style={styles.partTitle}>운동 스마트</h3>
          <p style={styles.partDesc}>운동 방법과 활동량 관리 팁</p>
          <Table
            size="small"
            pagination={false}
            dataSource={smartList.length ? smartList : dummySmart}
            rowKey="postid"
            columns={[
              { title: "게시글번호", dataIndex: "postid" },
              { title: "제목", dataIndex: "etitle" },
              { title: "내용", dataIndex: "econtent" },
            ]}
          />
          <div style={styles.textEnd}>
            <Link href="/smart" style={styles.moreBtn}>
              더보기
            </Link>
          </div>
        </div>

        {/* 질환 정보 */}
        <div style={styles.part}>
          <h3 style={styles.partTitle}>질환 정보</h3>
          <p style={styles.partDesc}>질환 관련 정보를 확인해 보세요</p>
          <Table
            size="small"
            pagination={false}
            dataSource={diseaseList.length ? diseaseList : dummyDisease}
            rowKey="id"
            columns={[
              { title: "질환명", dataIndex: "disname" },
              { title: "질환설명", dataIndex: "disex" },
              { title: "반려동물 종", dataIndex: "kindpet" },
            ]}
          />
          <div style={styles.textEnd}>
            <Link href="/disease" style={styles.moreBtn}>
              더보기
            </Link>
          </div>
        </div>
      </section>
    </div>
  );
}
