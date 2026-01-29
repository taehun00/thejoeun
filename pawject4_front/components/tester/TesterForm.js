// components/tester/TesterForm.js
import { useEffect, useMemo } from "react";
import { Form, Input, Select, Upload, Button, Space, Typography } from "antd";
import { UploadOutlined } from "@ant-design/icons";
import { fileUrl } from "../../utils/fileUrl";

const { Text } = Typography;
const { TextArea } = Input;
const { Option } = Select;

/**
 * TesterForm (관리자/유저 공용)
 *
 * props
 * - mode: "admin" | "user"
 * - isEdit: boolean
 * - initialValues?: dto
 * - categoryOptions?: string[]  (관리자용)
 * - onSubmit: ({ dto, files, keepImgIds }) => void
 * - loading?: boolean
 */

export default function TesterForm({
  mode = "user",
  isEdit = false,
  initialValues,
  categoryOptions = [],
  onSubmit,
  loading = false,
}) {
  const [form] = Form.useForm();
  const isAdmin = mode === "admin";

  // 초기값 세팅
  useEffect(() => {
    if (!initialValues) return;

    form.setFieldsValue({
      category: initialValues.category ?? undefined,
      title: initialValues.title ?? "",
      content: initialValues.content ?? "",
      foodid: initialValues.foodid ?? 0,

      // 관리자 전용
      isnotice:
        initialValues.isnotice !== undefined && initialValues.isnotice !== null
          ? Number(initialValues.isnotice)
          : 0,
      status:
        initialValues.status !== undefined && initialValues.status !== null
          ? Number(initialValues.status)
          : 0,
    });
  }, [initialValues, form]);

  // category watch (공지->모집상태 비활성화)
  const category = Form.useWatch("category", form);

  // 기존 이미지(수정용) - DTO  imgList[{testerimgid,imgsrc}]
  const existingImages = useMemo(() => {
    const arr = initialValues?.imgList || [];
    if (!Array.isArray(arr)) return [];
    return arr
      .map((x) => x?.imgsrc)
      .filter(Boolean);
  }, [initialValues]);

  const handleFinish = (values) => {
    const dto = {
      category: isAdmin ? values.category : "후기",
      title: values.title,
      content: values.content,
    };

    if (isAdmin) {
      dto.foodid = Number(values.foodid || 0);

      dto.isnotice = Number(values.isnotice ?? 0);

      // 공지글이면 모집상태는 의미없으니 0으로 고정(모집중)
      if (values.category === "공지") {
        dto.status = 0;
      } else {
        dto.status = Number(values.status ?? 0);
      }

      // 관리자 글: posttype = 1 고정
      dto.posttype = 1;
    }

    const files = (values.fileList || [])
      .map((f) => f?.originFileObj)
      .filter(Boolean);

    // keepImgIds: 수정이면 기존 이미지 전부 유지
    const keepImgIds = isEdit
      ? (initialValues?.imgList || [])
          .map((x) => x?.testerimgid)
          .filter((x) => x !== undefined && x !== null)
      : [];

    onSubmit?.({ dto, files, keepImgIds });
  };

  return (
    <Form
      form={form}
      layout="vertical"
      onFinish={handleFinish}
      initialValues={{
        category: undefined,
        title: "",
        content: "",
        foodid: 0,
        isnotice: 0,
        status: 0,
        fileList: [],
      }}
    >
      {/* 관리자-카테고리 */}
      {isAdmin && (
        <Form.Item
          label="카테고리"
          name="category"
          rules={[{ required: true, message: "카테고리를 선택하세요." }]}
        >
          <Select placeholder="카테고리 선택" style={{ width: 200 }}>
            {categoryOptions.map((c) => (
              <Option key={c} value={c}>
                {c}
              </Option>
            ))}
          </Select>
        </Form.Item>
      )}

      {/* 제목 */}
      <Form.Item
        label="제목"
        name="title"
        rules={[{ required: true, message: "제목을 입력하세요." }]}
      >
        <Input placeholder="제목 입력" maxLength={80} />
      </Form.Item>

      {/* 내용 */}
      <Form.Item
        label="내용"
        name="content"
        rules={[{ required: true, message: "내용을 입력하세요." }]}
      >
        <TextArea rows={10} placeholder="내용 입력" />
      </Form.Item>

      {/* 관리자 - 공지 여부 + 모집상태 */}
      {isAdmin && (
        <div style={{ display: "flex", gap: 16, flexWrap: "wrap" }}>
          <Form.Item label="상단공지" name="isnotice" style={{ width: 180 }}>
            <Select>
              <Option value={0}>공지 아님</Option>
              <Option value={1}>상단공지</Option>
            </Select>
          </Form.Item>

          <Form.Item
            label="모집상태"
            name="status"
            style={{ width: 180 }}
            tooltip="공지글은 모집상태가 적용되지 않습니다."
          >
            <Select disabled={category === "공지"}>
              <Option value={0}>모집중</Option>
              <Option value={1}>모집완료</Option>
            </Select>
          </Form.Item>
        </div>
      )}

      {/* 관리자- foodid */}
      {isAdmin && (
        <Form.Item label="연관 사료(foodid)" name="foodid">
          <Input type="number" min={0} placeholder="없으면 0" style={{ width: 220 }} />
        </Form.Item>
      )}

      {/* 기존 이미지 표시 (수정일 때만) */}
      {isEdit && existingImages.length > 0 && (
        <div style={{ marginBottom: 12 }}>
          <Text type="secondary">기존 이미지</Text>
          <div style={{ display: "flex", gap: 10, flexWrap: "wrap", marginTop: 8 }}>
            {existingImages.map((src, idx) => (
              <img
                key={`${src}-${idx}`}
                src={fileUrl(src)}
                alt="tester-old"
                style={{
                  width: 120,
                  height: 90,
                  objectFit: "cover",
                  borderRadius: 8,
                  border: "1px solid #eee",
                }}
              />
            ))}
          </div>

          <div style={{ marginTop: 8 }}>
            <Text type="secondary" style={{ fontSize: 12 }}>
              ※ 새 이미지를 업로드하면 기존 이미지는 추가됩니다.(유지 + 신규추가 방식)
            </Text>
          </div>
        </div>
      )}

      {/* 업로드 */}
      <Form.Item
        label="이미지"
        name="fileList"
        valuePropName="fileList"
        getValueFromEvent={(e) => e?.fileList}
      >
        <Upload multiple listType="picture" beforeUpload={() => false}>
          <Button icon={<UploadOutlined />}>이미지 선택</Button>
        </Upload>
      </Form.Item>

      {/* 버튼 */}
      <Form.Item>
        <Space>
          <Button type="primary" htmlType="submit" loading={loading}>
            {isEdit ? "수정" : "등록"}
          </Button>
          <Button htmlType="button" onClick={() => form.resetFields()}>
            초기화
          </Button>
        </Space>
      </Form.Item>
    </Form>
  );
}
