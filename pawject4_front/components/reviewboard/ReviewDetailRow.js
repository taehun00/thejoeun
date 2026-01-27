import { Button, Popconfirm, Descriptions } from "antd";

export default function ReviewDetailRow({
  review,
  loginRole,
  loginUserId,
  onOpenEditModal,
  onDelete,
  deleteLoading = false,
}) {
  if (!review) return null;

  const canEditDelete = () => {
    if (loginRole === "ROLE_ADMIN") return true;
    if (loginRole === "ROLE_MEMBER" && Number(review.userid) === Number(loginUserId)) return true;
    return false;
  };

  const openImg = (url) => {
    window.open(url, "_blank", "width=800,height=600,toolbar=no,menubar=no,resizable=yes");
  };

  return (
    <div style={{ display: "flex", gap: 16 }}>
      {/* 사료 이미지 */}
      <div style={{ width: 160, flexShrink: 0 }}>
        <img
          src={`/foodimg/${review.foodimg}`}
          alt="food"
          style={{ width: "100%", borderRadius: 12, cursor: "pointer" }}
          onClick={(e) => {
            e.stopPropagation();
            openImg(`/foodimg/${review.foodimg}`);
          }}
        />
      </div>

      <div style={{ flex: 1 }}>
        {/* 날짜(상세로 이동) */}
        <Descriptions size="small" column={2} style={{ marginBottom: 8 }}>
          <Descriptions.Item label="등록일">{review.createdat || "-"}</Descriptions.Item>
          <Descriptions.Item label="수정일">{review.updatedat || "-"}</Descriptions.Item>
        </Descriptions>

        {/* 리뷰 이미지 */}
        <div style={{ display: "flex", flexWrap: "wrap", gap: 10, marginBottom: 12 }}>
          {Array.isArray(review.reviewimglist) &&
            review.reviewimglist.map((img) => (
              <img
                key={img.reviewimgid ?? img.reviewimgname}
                src={`/upload/${img.reviewimgname}`}
                alt="review"
                style={{ width: 120, height: 120, objectFit: "cover", borderRadius: 12, cursor: "pointer" }}
                onClick={(e) => {
                  e.stopPropagation();
                  openImg(`/upload/${img.reviewimgname}`);
                }}
              />
            ))}
        </div>

        <div style={{ whiteSpace: "pre-wrap", lineHeight: 1.6 }}>{review.reviewcomment}</div>

        {/* 수정/삭제 */}
        {canEditDelete() && (
          <div style={{ display: "flex", justifyContent: "flex-end", gap: 8, marginTop: 24 }}>
            <Button
              onClick={(e) => {
                e.stopPropagation();
                onOpenEditModal?.(review.reviewid);
              }}
            >
              수정
            </Button>

            <Popconfirm
              title="정말 삭제하시겠습니까?"
              okText="삭제"
              cancelText="취소"
              onConfirm={() => onDelete?.(review.reviewid)}
            >
              <Button danger loading={deleteLoading} onClick={(e) => e.stopPropagation()}>
                삭제
              </Button>
            </Popconfirm>
          </div>
        )}
      </div>
    </div>
  );
}
