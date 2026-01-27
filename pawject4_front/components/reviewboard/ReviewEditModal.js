// components/reviewboard/ReviewEditModal.jsx
import { useEffect, useMemo, useState } from "react";
import { Modal, Form, Input, Select, Rate, Upload, Button, Space, Typography } from "antd";
import { UploadOutlined } from "@ant-design/icons";

const { Text } = Typography;

export default function ReviewEditModal({
  open,
  onClose,

  reviewid,
  formData,        // store.review.formData
  loading,         // store.review.loading
  editLoading,     // store.review.editLoading

  onFetchForm,     // (reviewid) => dispatch(fetchReviewFormRequest({reviewid}))
  onSubmitEdit,    // ({reviewid, dto, files, keepImgIds}) => dispatch(updateReviewRequest(...))
}) {
  const [form] = Form.useForm();
  const [files, setFiles] = useState([]); // 새로 업로드할 파일들

  // 기존 이미지 중 유지할 id 목록
  const [keepImgIds, setKeepImgIds] = useState([]);

  // 모달 열리면 수정폼 데이터 로드
  useEffect(() => {
    if (open && reviewid) {
      onFetchForm?.(reviewid);
    }
  }, [open, reviewid]);

  // 이미지 목록
  const imglist = useMemo(() => formData?.imglist || [], [formData]);

  // formData 들어오면 폼 채우기 + keepImgIds 초기화
  useEffect(() => {
    const dto = formData?.dto;
    if (!dto) return;

    form.setFieldsValue({
      pettypeid: dto.pettypeid,
      brandid: dto.brandid,
      foodid: dto.foodid,
      rating: dto.rating,
      title: dto.title,
      reviewcomment: dto.reviewcomment,
    });

    // 초기에는 기존 이미지 전부 유지
    const initKeep = (formData?.imglist || []).map((img) => img.reviewimgid);
    setKeepImgIds(initKeep);

    // 새 업로드 파일 초기화(안전)
    setFiles([]);
  }, [formData]);

  // Upload 컴포넌트 연결 (자동업로드 방지)
  const uploadProps = {
    multiple: true,
    fileList: files,
    beforeUpload: (file) => {
      setFiles((prev) => [...prev, file]);
      return false;
    },
    onRemove: (file) => {
      setFiles((prev) => prev.filter((f) => f.uid !== file.uid));
    },
  };

  const toggleKeep = (imgid) => {
    setKeepImgIds((prev) => {
      if (prev.includes(imgid)) return prev.filter((id) => id !== imgid);
      return [...prev, imgid];
    });
  };

  const handleOk = async () => {
    const values = await form.validateFields();

    const dto = {
      pettypeid: values.pettypeid,
      brandid: values.brandid,
      foodid: values.foodid,
      rating: values.rating,
      title: values.title,
      reviewcomment: values.reviewcomment,
    };

    onSubmitEdit?.({ reviewid, dto, files, keepImgIds });
  };

  return (
    <Modal
      title="리뷰 수정"
      open={open}
      onCancel={onClose}
      onOk={handleOk}
      confirmLoading={editLoading}
      okText="수정"
      cancelText="취소"
      width={800}
    >
      <Form form={form} layout="vertical">
        <Space size={12} style={{ width: "100%" }} align="start">
          <Form.Item
            label="종"
            name="pettypeid"
            rules={[{ required: true, message: "종을 선택하세요" }]}
            style={{ flex: 1 }}
          >
            <Select
              options={[
                { value: 1, label: "고양이" },
                { value: 2, label: "강아지" },
              ]}
            />
          </Form.Item>

          <Form.Item
            label="브랜드"
            name="brandid"
            rules={[{ required: true, message: "브랜드를 선택하세요" }]}
            style={{ flex: 1 }}
          >
            <Select
              options={(formData?.brandlist || []).map((b) => ({
                value: b.brandid,
                label: b.brandname,
              }))}
            />
          </Form.Item>

          <Form.Item
            label="사료"
            name="foodid"
            rules={[{ required: true, message: "사료를 선택하세요" }]}
            style={{ flex: 2 }}
          >
            <Select
              showSearch
              optionFilterProp="label"
              options={(formData?.foodlist || []).map((f) => ({
                value: f.foodid,
                label: f.foodname,
              }))}
            />
          </Form.Item>
        </Space>

        <Form.Item
          label="평점"
          name="rating"
          rules={[{ required: true, message: "평점을 선택하세요" }]}
        >
          <Rate />
        </Form.Item>

        <Form.Item
          label="제목"
          name="title"
          rules={[{ required: true, message: "제목을 입력하세요" }]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          label="리뷰 내용"
          name="reviewcomment"
          rules={[{ required: true, message: "리뷰 내용을 입력하세요" }]}
        >
          <Input.TextArea rows={5} />
        </Form.Item>

        {/* 기존 이미지 */}
        <div style={{ marginBottom: 12 }}>
          <Text strong>기존 이미지</Text>

          <div style={{ display: "flex", gap: 10, flexWrap: "wrap", marginTop: 8 }}>
            {imglist.length === 0 ? (
              <Text type="secondary">등록된 이미지 없음</Text>
            ) : (
              imglist.map((img) => {
                const keep = keepImgIds.includes(img.reviewimgid);

                return (
                  <div
                    key={img.reviewimgid}
                    style={{
                      position: "relative",
                      width: 120,
                      height: 120,
                      borderRadius: 8,
                      overflow: "hidden",
                      border: keep ? "2px solid #1677ff" : "2px dashed #aaa",
                      opacity: keep ? 1 : 0.35,
                      cursor: "pointer",
                    }}
                    onClick={() => toggleKeep(img.reviewimgid)}
                    title={keep ? "유지" : "삭제(제외)"}
                  >
                    <img
                      src={`/upload/${img.reviewimgname}`}
                      alt="review"
                      style={{ width: "100%", height: "100%", objectFit: "cover" }}
                      onClick={(e) => {
                        e.stopPropagation();
                        window.open(`/upload/${img.reviewimgname}`, "_blank");
                      }}
                    />

                    <div style={{ position: "absolute", bottom: 6, left: 6 }}>
                      <Button size="small" type={keep ? "primary" : "default"}>
                        {keep ? "유지" : "삭제"}
                      </Button>
                    </div>
                  </div>
                );
              })
            )}
          </div>

          <div style={{ marginTop: 6 }}>
            <Text type="secondary">
              ※ 클릭하면 유지/삭제 토글됩니다. 
            </Text>
          </div>
        </div>

        {/* 새 이미지 */}
        <div style={{ marginBottom: 6 }}>
          <Text strong>새 이미지 업로드 </Text>
        </div>

        <Upload {...uploadProps} listType="picture">
          <Button icon={<UploadOutlined />}>이미지 선택</Button>
        </Upload>
      </Form>
    </Modal>
  );
}
