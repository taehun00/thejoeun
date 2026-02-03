import React from "react";
import { Card, Result, Button, Space, Typography } from "antd";
import { ToolOutlined, HomeOutlined } from "@ant-design/icons";
import { useRouter } from "next/router";

const { Text } = Typography;

export default function ExecPreparingPage() {
  const router = useRouter();

  return (
    <div style={{ padding: 24, maxWidth: 900, margin: "0 auto" }}>
      <Card>
        <Result
          icon={<ToolOutlined />}
          title="운동 챌린지 게시판은 현재 보수공사 중입니다"
          subTitle={
            <Text type="secondary">
              현재 기능 개선 및 개편 작업 중입니다. 빠른 시일 내에 이용하시는데 불편함이 없는 게시판으로 오픈하겠습니다.
            </Text>
          }
          extra={
            <Space>
              <Button onClick={() => router.back()}>이전으로</Button>
              <Button type="primary" icon={<HomeOutlined />} onClick={() => router.push("/mainpage")}>
                홈으로
              </Button>
            </Space>
          }
        />
      </Card>
    </div>
  );
}