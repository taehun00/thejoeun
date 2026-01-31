// components/tester/TesterDetailPage.js
import { useEffect, useMemo, useState, useCallback } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useRouter } from "next/router";
import {
  Button,
  Space,
  Spin,
  Alert,
  Typography,
  Tag,
  Popconfirm,
  Image,
  Card,
} from "antd";

import { fileUrl } from "../../utils/fileUrl";
import { parseJwt } from "../../utils/jwt";

import {
  fetchTesterDetailRequest,
  toggleTesterNoticeRequest,
  toggleTesterStatusRequest,
  deleteTesterRequest,
} from "../../reducers/tester/testerReducer";

const { Title, Text } = Typography;

function categoryToTag(category) {
  if (category === "공지") return <Tag color="gold">공지</Tag>;
  if (category === "모집") return <Tag color="green">모집중</Tag>;
  if (category === "모집완료") return <Tag color="default">모집완료</Tag>;
  if (category === "후기") return <Tag>후기</Tag>;
  return <Tag>{category || "-"}</Tag>;
}

function extractUserid(payload) {
  if (!payload) return null;

  const candidates = [
    payload.userid,
    payload.userId,
    payload.id,
    payload.sub,
  ];

  for (const v of candidates) {
    if (v === null || v === undefined) continue;
    const s = String(v).trim();
    if (/^\d+$/.test(s)) return Number(s);
  }
  return null;
}

export default function TesterDetailPage() {
  const router = useRouter();
  const dispatch = useDispatch();
  const { testerid } = router.query;

  const { detail, noticeLoading, statusLoading, deleteLoading } = useSelector(
    (state) => state.tester
  );

  const [loginRole, setLoginRole] = useState(null);
  const [loginUserid, setLoginUserid] = useState(null);

  useEffect(() => {
    if (typeof window === "undefined") return;

    const token = localStorage.getItem("accessToken");
    const payload = token ? parseJwt(token) : null;

    setLoginRole(payload?.role ?? payload?.auth ?? payload?.authority ?? null);
    setLoginUserid(extractUserid(payload));
  }, []);

  const isAdmin = loginRole === "ROLE_ADMIN";

  useEffect(() => {
    if (!testerid) return;
    dispatch(fetchTesterDetailRequest({ testerid }));
  }, [dispatch, testerid]);

  const dto = detail?.dto;

  const ownerUserid = dto?.userid ?? null;

  const isOwner = useMemo(() => {
    if (loginUserid === null || loginUserid === undefined) return false;
    if (ownerUserid === null || ownerUserid === undefined) return false;
    return Number(loginUserid) === Number(ownerUserid);
  }, [loginUserid, ownerUserid]);

const isAdminPost = Number(dto?.posttype) === 1; // 운영글 여부
const canEdit = (isAdmin && isAdminPost) || isOwner;
const canDelete = isAdmin || isOwner;

const canAdminToggle = isAdmin && isAdminPost;

  const imgList = useMemo(() => {
    const arr = dto?.imgList || [];
    if (!Array.isArray(arr)) return [];
    return arr.map((x) => x?.imgsrc).filter(Boolean);
  }, [dto]);

  const createdDate = dto?.createdat ? String(dto.createdat).slice(0, 10) : "-";
  const updatedDate = dto?.updatedat ? String(dto.updatedat).slice(0, 10) : "-";

  const handleToggleNotice = useCallback(() => {
    dispatch(toggleTesterNoticeRequest({ testerid, mode: "admin" }));   
  }, [dispatch, testerid]);

  const handleToggleStatus = useCallback(() => {
    dispatch(toggleTesterStatusRequest({ testerid, mode: "admin" }));   
  }, [dispatch, testerid]);

  const handleDelete = useCallback(() => {
    dispatch(deleteTesterRequest({ testerid }));
    router.push("/tester");
  }, [dispatch, testerid, router]);

  const onEdit = useCallback(() => {
    router.push(`/tester/edit/${testerid}`);
  }, [router, testerid]);

  return (
    <div style={{ width: "min(980px, 94vw)", margin: "28px auto 60px" }}>
      {/* 상단 네비 */}
      <div
        style={{
          display: "flex",
          justifyContent: "flex-end",
          gap: 12,
          marginBottom: 14,
        }}
      >
          {/* 관리자 토글 (있으면 제목/태그 라인 다음에 붙임) */}
          {canAdminToggle && (
            <div style={{ marginTop: 10 }}>
              <Space>
                <Button
                  size="small"
                  loading={noticeLoading}
                  onClick={handleToggleNotice}
                  type="primary" 
                  style={{ background:"#fa8c16", borderColor:"#fa8c16" }}
                >
                  {Number(dto?.isnotice) === 1 ? "공지내림" : "공지올림"}
                </Button>

                <Button
                  size="small"
                  loading={statusLoading}
                  onClick={handleToggleStatus}
                  type="primary" 
                  style={{ background:"#52c41a", borderColor:"#52c41a" }}
                >
                  {Number(dto?.status) === 1 ? "모집완료" : "모집중"}
                </Button>
              </Space>
            </div>
          )}

                  <Button onClick={() => router.push("/tester")}>목록</Button>




      </div>

      {detail?.loading && (
        <Card style={{ borderRadius: 14 }}>
          <div style={{ textAlign: "center", padding: 24 }}>
            <Spin />
          </div>
        </Card>
      )}

      {!detail?.loading && detail?.error && (
        <Alert type="error" message={detail.error} showIcon />
      )}

      {!detail?.loading && !detail?.error && dto && (
        <Card
          style={{
            borderRadius: 16,
            boxShadow: "0 4px 18px rgba(0,0,0,0.06)",
          }}
          bodyStyle={{ padding: 22 }}
        >
          {/* 제목 */}
          <Title level={3} style={{ margin: 0, lineHeight: 1.25 }}>
            {categoryToTag(dto?.category)} {dto?.title || "(제목 없음)"}
          </Title>

          {/* 부가정보 */}
          <div style={{ marginTop: 10, display: "flex", gap: 10, flexWrap: "wrap" }}>

            {canAdminToggle && Number(dto?.isnotice) === 1 && (
              <Tag color="red">상단공지</Tag>
            )}

            {canAdminToggle && (
              <Tag color={Number(dto?.status) === 0 ? "green" : "default"}>
                {Number(dto?.status) === 0 ? "모집중" : "모집완료"}
              </Tag>
            )}

          <Text type="secondary">
            {dto?.nickname || "-"} · 작성 {createdDate} · 수정 {updatedDate} · 조회 {dto?.views ?? 0}
          </Text>
          </div>


          {/*  내용 */}
          <div
            style={{
              marginTop: 16,
              padding: "16px 16px",
              borderRadius: 14,
              background: "#fafafa",
              border: "1px solid #f0f0f0",
            }}
          >
            {imgList.length > 0 && (
              <div style={{ marginBottom: 14 }}>
                <Image.PreviewGroup>
                  <div
                    style={{
                      display: "grid",
                      gridTemplateColumns: "repeat(auto-fill, minmax(160px, 1fr))",
                      gap: 10,
                    }}
                  >
                    {imgList.map((src, idx) => (
                      <Image
                        key={`${src}-${idx}`}
                        src={fileUrl(src)}
                        alt="tester-img"
                        style={{
                          width: "100%",
                          height: 140,
                          objectFit: "cover",
                          borderRadius: 10,
                        }}
                      />
                    ))}
                  </div>
                </Image.PreviewGroup>
              </div>
            )}

            <div
              style={{
                minHeight: 180,
                whiteSpace: "pre-wrap",
                lineHeight: 1.8,
                fontSize: 14,
              }}
            >
              {dto?.content || "-"}
            </div>
          </div>

          {/* 하단 (버튼) */}
          <div
            style={{
              marginTop: 14,
              display: "flex",
              justifyContent: "flex-end",
              gap: 10,
              flexWrap: "wrap",
            }}
          >
            <Space>
              {canEdit && <Button onClick={onEdit}>수정</Button>}
              {canDelete && (
                <Popconfirm
                  title="삭제할까요?"
                  okText="삭제"
                  cancelText="취소"
                  onConfirm={handleDelete}
                >
                  <Button danger loading={deleteLoading}>
                    삭제
                  </Button>
                </Popconfirm>
              )}
            </Space>
          </div>
        </Card>
      )}
    </div>
  );
}