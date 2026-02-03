import { Table, Button } from "antd";
import React, { useState, useEffect } from "react";
import Link from "next/link";

const styles = {
  page: {
    fontFamily: '"Segoe UI", Roboto, -apple-system, BlinkMacSystemFont, sans-serif',
    background: "#eef2f7",
    color: "#333",
    minHeight: "100vh",
    padding: "40px 20px",
  },

  subArea: {
    maxWidth: 960,
    margin: "0 auto 50px",
    display: "flex",
    gap: 16,
    justifyContent: "center",
    flexWrap: "wrap",
  },

  mainActionBtn: {
    display: "inline-block",
    padding: "14px 28px",
    borderRadius: 8,
    fontSize: 15,
    fontWeight: 600,
    textAlign: "center",
    textDecoration: "none",
    color: "#fff",
    background: "#4c6fbf",
    boxShadow: "0 4px 12px rgba(0,0,0,0.15)",
    transition: "all 0.25s ease",
    cursor: "pointer",
    minWidth: 130,
  },

  mainActionBtnYellow: {
    background: "#f4c430",
    color: "#2b2b2b",
  },

  mainSections: {
    maxWidth: 1200,
    margin: "0 auto",
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(280px, 1fr))",
    gap: 28,
  },

  part: {
    background: "#ffffff",
    borderRadius: 12,
    display: "flex",
    flexDirection: "column",
    boxShadow: "0 10px 25px rgba(0,0,0,0.08)",
    transition: "transform 0.25s ease, box-shadow 0.25s ease",
    minHeight: 320,
  },

  partHeader: {
    padding: "18px 20px",
    borderBottom: "2px solid #e0e0e0",
    fontSize: 18,
    fontWeight: 700,
    color: "#2b3a67",
    textAlign: "center", // 중앙 정렬
  },

  partBody: {
    padding: "20px 30px", // 양옆 여백 넓힘
    flexGrow: 1,
    fontSize: 14,
    color: "#555",
    lineHeight: 1.7,
    textAlign: "justify", // 정렬 깔끔하게
    whiteSpace: "pre-line", // 엔터(\n) 적용
  },

  partFooter: {
    padding: "10px 20px 20px",
    textAlign: "right",
  },

  moreBtn: {
    fontSize: 13,
    padding: "6px 16px",
    background: "#4c6fbf",
    color: "#fff",
    borderRadius: 8,
    textDecoration: "none",
    transition: "background 0.2s ease",
  },
};

export default function MainPage() {
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

  const cardData = [
    {
      title: "사료 리뷰",
      description: "사료 리뷰 게시판에서는 다양한 사료에 대한 실제 사용자 리뷰와 평가를 확인할 수 있습니다.\n반려동물에게 맞는 사료 선택에 도움이 되는 정보를 제공합니다.",
      link: "/reviewboard",
    },
    {
      title: "운동 스마트",
      description: "운동 스마트 게시판은 반려동물의 건강을 위한 운동 방법, 활동량 관리 팁, 생활 습관 개선 정보를 제공합니다.\n반려동물의 체력과 활력을 관리하는 데 유용한 정보를 확인할 수 있습니다.",
      link: "/smart",
    },
    {
      title: "질환 정보",
      description: "질환 정보 게시판에서는 반려동물의 주요 질환과 예방 방법, 관리법, 증상 등을 안내합니다.\n반려동물의 건강 관리와 질환 예방에 유용한 정보를 제공합니다.",
      link: "/petdisease",
    },
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
        {cardData.map((card, idx) => (
          <div
            key={idx}
            style={styles.part}
            onMouseEnter={(e) => e.currentTarget.style.transform = "translateY(-5px)"}
            onMouseLeave={(e) => e.currentTarget.style.transform = "translateY(0px)"}
          >
            <div style={styles.partHeader}>{card.title}</div>
            <div style={styles.partBody}>{card.description}</div>
            <div style={styles.partFooter}>
              <Link href={card.link}>
                <a style={styles.moreBtn}>더보기</a>
              </Link>
            </div>
          </div>
        ))}
      </section>
    </div>
  );
}
