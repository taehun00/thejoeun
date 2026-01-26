// components/AppLayout.js
import { Layout, Menu, Drawer, Button, Grid } from "antd";
import { MenuOutlined } from "@ant-design/icons";
import Link from "next/link";
import { useRouter } from "next/router";
import { useMemo, useState } from "react";

const { Header, Content } = Layout;
const { useBreakpoint } = Grid;

export default function AppLayout({ children }) {
  const router = useRouter();
  const screens = useBreakpoint();

  const [drawerOpen, setDrawerOpen] = useState(false);

  // 메뉴 구성 
  const menuItems = useMemo(
    () => [
      { key: "/foodboard", label: <Link href="/foodboard">사료관리</Link> },
      { key: "/reviewboard", label: <Link href="/reviewboard">사료리뷰</Link> },
      { key: "/disease", label: <Link href="/disease">질환리스트</Link> },
      { key: "/faq", label: <Link href="/faq">고객센터</Link> },
    ],
    []
  );

  // 현재 경로에 따른 active 메뉴 키
  const selectedKeys = useMemo(() => {
    // 정확히 매칭되는 키가 있으면 그걸 선택
    const exact = menuItems.find((m) => m.key === router.pathname);
    if (exact) return [exact.key];

    // /foodboard/detail/... 이런 하위 경로 처리
    const found = menuItems.find((m) => router.pathname.startsWith(m.key) && m.key !== "/");
    return found ? [found.key] : ["/"];
  }, [router.pathname, menuItems]);

  return (
    <Layout style={{ minHeight: "100vh" }}>
      {/* Header */}
      <Header
        style={{
          padding: "0 20px",
          height: 64,
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
        }}
      >
        {/* 로고 겸 홈버튼 */}
        <div style={{ display: "flex", alignItems: "center", gap: 12 }}>
          <Link href="/" legacyBehavior>
            <a style={{ color: "#fff", fontWeight: 800, fontSize: 18, textDecoration: "none" }}>
              🐾 Petfood&health
            </a>
          </Link>
        </div>

        {/* 메뉴 (PC) */}
        {screens.md ? (
          <Menu
            theme="dark"
            mode="horizontal"
            items={menuItems}
            selectedKeys={selectedKeys}
            style={{ flex: 1, justifyContent: "flex-end" }}
          />
        ) : (
          <Button
            type="text"
            icon={<MenuOutlined style={{ color: "white", fontSize: 20 }} />}
            onClick={() => setDrawerOpen(true)}
          />
        )}
      </Header>

      {/* 모바일 Drawer */}
      <Drawer
        title="MENU"
        placement="right"
        onClose={() => setDrawerOpen(false)}
        open={drawerOpen}
      >
        <Menu
          mode="vertical"
          items={menuItems}
          selectedKeys={selectedKeys}
          onClick={() => setDrawerOpen(false)}
        />
      </Drawer>

      {/* Content */}
      <Content style={{ padding: "28px 16px" }}>
        <div style={{ maxWidth: 1200, margin: "0 auto" }}>{children}</div>
      </Content>
    </Layout>
  );
}
