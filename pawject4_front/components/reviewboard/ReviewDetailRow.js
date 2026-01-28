import { Button, Popconfirm, Descriptions } from "antd";
import { fileUrl } from "../../utils/fileUrl";

export default function ReviewDetailRow({
  review,
  loginRole,
  loginUserId,
  onOpenEditModal,
  onDelete,
  deleteLoading = false,
}) {
  if (!review) return null;

  const canEditDelete =
    loginRole === "ROLE_ADMIN" ||
    (loginRole === "ROLE_MEMBER" && Number(review.userid) === Number(loginUserId));

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
          style={{
            width: "100%",
            height: 160,
            objectFit: "cover",
            borderRadius: 12,
            cursor: "pointer",
          }}
          onClick={(e) => {
            e.stopPropagation();
            openImg(`/foodimg/${review.foodimg}`);
          }}
          onError={(e) => {
            e.currentTarget.style.display = "none";
          }}
        />
      </div>

      <div style={{ flex: 1, display: "flex", flexDirection: "column", minWidth: 0 }}>
        {/* 리뷰 이미지 */}
        <div
          style={{
            display: "flex",
            justifyContent: "flex-end",
            marginBottom: 10,
          }}
        >
          <div
            style={{
              display: "flex",
              flexWrap: "wrap",
              justifyContent: "flex-end",
              gap: 6,
              maxWidth: 320, // 너무 가로로 길어지지 않게 제한
            }}
          >
            {Array.isArray(review.reviewimglist) &&
              review.reviewimglist.map((img) => (
                <img
                  key={img.reviewimgid ?? img.reviewimgname}
                  src={fileUrl(img.reviewimgname)} 
                  alt="review"
                  style={{
                    width: 64,
                    height: 64,
                    objectFit: "cover",
                    borderRadius: 10,
                    cursor: "pointer",
                    border: "1px solid #e5e7eb",
                  }}
                  onClick={(e) => {
                    e.stopPropagation();
                    openImg(fileUrl(img.reviewimgname));
                  }}
                  onError={(e) => {
                    e.currentTarget.style.display = "none";
                  }}
                />
              ))}
          </div>
        </div>

        {/* 리뷰 내용 */}
        <div style={{ whiteSpace: "pre-wrap", lineHeight: 1.6, wordBreak: "break-word" }}>
          {review.reviewcomment}
        </div>

        {/* 버튼 */}
        {canEditDelete && (
          <div style={{ display: "flex", justifyContent: "flex-end", gap: 8, marginTop: 14 }}>
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
