// components/petfoodsearch/PetfoodSearchAiBox.jsx
import { useEffect, useMemo, useState } from "react";
import { Card, Button, Input, Space, Typography, Alert, Divider } from "antd";
import { RobotOutlined } from "@ant-design/icons";

const { Text, Paragraph } = Typography;

export default function PetfoodSearchAiBox({
  open,
  loading,
  result,
  error,
  onAsk,
  onApplyAiFilters,
}) {
  const [panelOpen, setPanelOpen] = useState(false); // thymeleaf openBtn 역할
  const [userMessage, setUserMessage] = useState("");


  const displayMessage = useMemo(() => {
    if (!result?.message) return "";
    return `${result.message}\n\n검색 버튼을 눌러 결과를 확인해 보세요.`;
  }, [result]);


  useEffect(() => {
    if (result?.filters) {
      onApplyAiFilters?.(result.filters);
    }
  }, [result, onApplyAiFilters]);

  const onClickAsk = () => {
    const msg = userMessage.trim();
    if (!msg) {
      alert("조건이 입력되지 않았습니다");
      return;
    }
    onAsk?.(msg);
  };

  return (
    <Card
      size="small"
      title={
        <Space>
          <RobotOutlined />
          <Text strong>AI</Text>
          <Text type="secondary">검색 조건 설정 도움</Text>
        </Space>
      }
      extra={
        <Button size="small" onClick={() => setPanelOpen((prev) => !prev)}>
          {panelOpen ? "닫기" : "열기"}
        </Button>
      }
    >
      {/* 닫힌 상태에서는 안내만 */}
      {!panelOpen && (
        <Text type="secondary">
          검색 조건을 자연어로 입력하면 AI가 필터를 추천합니다.
        </Text>
      )}

      {/* 열린 상태 */}
      {panelOpen && (
        <>
          <Paragraph style={{ marginBottom: 8 }}>
            필요한 조건을 설명해 주세요.
          </Paragraph>

          <Input.TextArea
            value={userMessage}
            onChange={(e) => setUserMessage(e.target.value)}
            placeholder="필요하신 조건을 설명해 주세요"
            rows={4}
          />

          <Space style={{ marginTop: 8 }}>
            <Button type="primary" loading={loading} onClick={onClickAsk}>
              질문하기
            </Button>
            <Button
              onClick={() => {
                setUserMessage("");
              }}
              disabled={loading}
            >
              초기화
            </Button>
          </Space>

          <Divider style={{ margin: "12px 0" }} />

          {/* error */}
          {error && (
            <Alert
              type="error"
              showIcon
              message="AI 요청 중 오류가 발생했습니다"
              description={String(error)}
              style={{ marginBottom: 12 }}
            />
          )}

          {/* result */}
          {displayMessage && (
            <Alert
              type="success"
              showIcon
              message="AI 추천 결과"
              description={
                <pre style={{ margin: 0, whiteSpace: "pre-wrap" }}>
                  {displayMessage}
                </pre>
              }
            />
          )}
        </>
      )}
    </Card>
  );
}
