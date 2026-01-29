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
  Divider,
  Image,
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

// 카테고리 태그
function categoryToTag(category) {
  if (category === "공지") return <Tag color="gold">공지</Tag>;
  if (category === "모집") return <Tag color="green">모집중</Tag>;
  if (category === "모집완료") return <Tag color="default">모집완료</Tag>;
  if (category === "후기") return <Tag>후기</Tag>;
  return <Tag>{category || "-"}</Tag>;
}

export default function TesterDetailPage() {
  const router = useRouter();
  const dispatch = useDispatch();

  const { testerid } = router.query;

  const { detail, noticeLoading, statusLoading, deleteLoading } = useSelector(
    (state) => state.tester
  );

  // 로그인 정보
  const [loginRole, setLoginRole] = useState(null);
  const [loginUserid, setLoginUserid] = useState(null);

  useEffect(() => {
    if (typeof window === "undefined") return;

    const token = localStorage.getItem("accessToken");
    const payload = token ? parseJwt(token) : null;

    setLoginRole(payload?.role ?? null);

    const uid = payload?.userid ?? payload?.userId ?? payload?.id ?? null;
    setLoginUserid(uid);
  }, []);

  const isAdmin = loginRole === "ROLE_ADMIN";

  // 상세 호출
  useEffect(() => {
    if (!testerid) return;

    dispatch(fetchTesterDetailRequest({ testerid }));
  }, [dispatch, testerid]);

  const dto = detail?.dto;

  // 소유자 판별
  const ownerUserid = dto?.userid ?? null;
  const isOwner = useMemo(() => {
    if (!loginUserid) return false;
    if (!ownerUserid) return false;
    return Number(loginUserid) === Number(ownerUserid);
  }, [loginUserid, ownerUserid]);

  const canEdit = isAdmin || isOwner;
  const canDelete = isAdmin || isOwner;

  // 관리자 토글 버튼 노출 조건
  const canAdminToggle = isAdmin && Number(dto?.posttype) === 1;

  // 이미지 목록
  const imgList = useMemo(() => {
    const arr = dto?.imgList || [];
    if (!Array.isArray(arr)) return [];
    return arr.map((x) => x?.imgsrc).filter(Boolean);
  }, [dto]);

  const createdDate = dto?.createdat ? String(dto.createdat).slice(0, 10) : "-";
  const updatedDate = dto?.updatedat ? String(dto.updatedat).slice(0, 10) : "-";

  const handleToggleNotice = useCallback(() => {
    dispatch(toggleTesterNoticeRequest({ testerid }));
  }, [dispatch, testerid]);

  const handleToggleStatus = useCallback(() => {
    dispatch(toggleTesterStatusRequest({ testerid }));
  }, [dispatch, testerid]);

  const handleDelete = useCallback(() => {
    dispatch(deleteTesterRequest({ testerid }));
    router.push("/tester");
  }, [dispatch, testerid, router]);

  const onEdit = useCallback(() => {
    router.push(`/tester/edit/${testerid}`);
  }, [router, testerid]);

  return (
    <div style={{ width: "80%", margin: "30px auto" }}>
      <div style={{ marginBottom: 16 }}>
        <Button onClick={() => router.push("/tester")}>목록</Button>
      </div>

      {/* 로딩/에러 */}
      {detail?.loading && (
        <div style={{ textAlign: "center", padding: 20 }}>
          <Spin />
        </div>
      )}

      {!detail?.loading && detail?.error && (
        <Alert type="error" message={detail.error} showIcon />
      )}

      {!detail?.loading && !detail?.error && dto && (
        <>
          {/* 제목 라인 */}
          <div style={{ display: "flex", alignItems: "baseline", gap: 10 }}>
            <div style={{ transform: "translateY(2px)" }}>
              {categoryToTag(dto?.category)}
            </div>

            <Title level={4} style={{ marginBottom: 6 }}>
              {dto?.title || "(제목 없음)"}
            </Title>
          </div>

          {/* 작성정보 + 관리자 토글 */}
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              gap: 12,
              flexWrap: "wrap",
            }}
          >
            <div style={{ display: "flex", alignItems: "center", gap: 10, flexWrap: "wrap" }}>
              <Text type="secondary">
                {dto?.nickname || "-"} / {createdDate} / 조회 {dto?.views ?? 0}
              </Text>

              {canAdminToggle && Number(dto?.isnotice) === 1 && <Tag color="red">상단공지</Tag>}

              {canAdminToggle && (
                <Tag color={Number(dto?.status) === 0 ? "green" : "default"}>
                  {Number(dto?.status) === 0 ? "모집중" : "모집완료"}
                </Tag>
              )}
            </div>

            {canAdminToggle && (
              <Space>
                <Button size="small" loading={noticeLoading} onClick={handleToggleNotice}>
                  {Number(dto?.isnotice) === 1 ? "공지내림" : "공지올림"}
                </Button>

                <Button size="small" loading={statusLoading} onClick={handleToggleStatus}>
                  {Number(dto?.status) === 1 ? "모집중으로" : "모집완료로"}
                </Button>
              </Space>
            )}
          </div>

          <Divider style={{ margin: "12px 0" }} />

          {/* 이미지 */}
          {imgList.length > 0 && (
            <div style={{ marginBottom: 16 }}>
              <Image.PreviewGroup>
                <div
                  style={{
                    display: "grid",
                    gridTemplateColumns: "repeat(auto-fill, minmax(170px, 1fr))",
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
                        height: 150,
                        objectFit: "cover",
                        borderRadius: 10,
                      }}
                    />
                  ))}
                </div>
              </Image.PreviewGroup>
            </div>
          )}

          {/* 내용 */}
          <div style={{ whiteSpace: "pre-wrap", lineHeight: 1.7, fontSize: 14 }}>
            {dto?.content || "-"}
          </div>

          <Divider style={{ margin: "12px 0" }} />

          {/* 수정/삭제 */}
          <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
            <Text type="secondary" style={{ fontSize: 12 }}>
              수정일: {updatedDate}
            </Text>

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
        </>
      )}
    </div>
  );
}
