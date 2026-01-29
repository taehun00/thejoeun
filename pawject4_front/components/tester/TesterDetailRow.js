// components/tester/TesterDetailRow.js
import { useEffect, useMemo, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
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

export default function TesterDetailRow({
  record, // 테이블 row record
  onEdit, // 수정 클릭 시 이동 콜백: (testerid)=>void
}) {
  const dispatch = useDispatch();

  const { detail, noticeLoading, statusLoading, deleteLoading } = useSelector(
    (state) => state.tester
  );

  const testerid = record?.testerid;

  // 현재 펼쳐진 row인지
  const isThisOpen = detail?.testerid === testerid;

  // 로그인 정보
  const [loginRole, setLoginRole] = useState(null);
  const [loginUserid, setLoginUserid] = useState(null);

  useEffect(() => {
    if (typeof window === "undefined") return;

    const token = localStorage.getItem("accessToken");
    const payload = token ? parseJwt(token) : null;

    setLoginRole(payload?.role ?? null);

    // userid 필드명 방어
    const uid = payload?.userid ?? payload?.userId ?? payload?.id ?? null;
    setLoginUserid(uid);
  }, []);

  const isAdmin = loginRole === "ROLE_ADMIN";

  // 열릴 때 상세 호출 (한번만)
  useEffect(() => {
    if (!testerid) return;
    if (!isThisOpen) return;
    if (detail?.dto) return;

    dispatch(fetchTesterDetailRequest({ testerid }));
  }, [dispatch, testerid, isThisOpen, detail?.dto]);

  if (!isThisOpen) return null;

  // 상세 dto가 있으면 그걸 우선
  const dto = detail?.dto || record;

  // 소유자 판별: dto.userid 우선, 없으면 record.userid
  const ownerUserid = dto?.userid ?? record?.userid ?? null;
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
    const arr = dto?.testerimg || dto?.testerimgList || dto?.imglist || [];
    if (!Array.isArray(arr)) return [];
    return arr
      .map((x) => (typeof x === "string" ? x : x?.imgsrc))
      .filter(Boolean);
  }, [dto]);

  const createdDate = dto?.createdat ? String(dto.createdat).slice(0, 10) : "-";
  const updatedDate = dto?.updatedat ? String(dto.updatedat).slice(0, 10) : "-";

  const handleToggleNotice = () => dispatch(toggleTesterNoticeRequest({ testerid }));
  const handleToggleStatus = () => dispatch(toggleTesterStatusRequest({ testerid }));
  const handleDelete = () => dispatch(deleteTesterRequest({ testerid }));

  return (
    <div style={{ padding: "12px 10px" }}>
      {/* 로딩/에러 */}
      {detail?.loading && (
        <div style={{ textAlign: "center", padding: 20 }}>
          <Spin />
        </div>
      )}

      {!detail?.loading && detail?.error && (
        <Alert type="error" message={detail.error} showIcon />
      )}

      {!detail?.loading && !detail?.error && (
        <>
          {/* 제목 라인 (관리자는 카테고리 Tag 작게 붙임) */}
          <div style={{ display: "flex", alignItems: "baseline", gap: 10 }}>
            {isAdmin && (
              <div style={{ transform: "translateY(2px)" }}>
                {categoryToTag(dto?.category)}
              </div>
            )}
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

              {/* 유저 화면에선 카테고리 표시 안함 */}
              {/* 관리자 토글 상태 표시 */}
              {canAdminToggle && Number(dto?.isnotice) === 1 && <Tag color="red">상단공지</Tag>}

              {canAdminToggle && (
                <Tag color={Number(dto?.status) === 0 ? "green" : "default"}>
                  {Number(dto?.status) === 0 ? "모집중" : "모집완료"}
                </Tag>
              )}
            </div>

            {/* 관리자 전용 토글 */}
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

          {/* ✅ 이미지: 위에 그리드 / 글 방해 X */}
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
              {canEdit && <Button onClick={() => onEdit?.(testerid)}>수정</Button>}

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