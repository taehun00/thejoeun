// components/ad/AdForm.js
// 광고 등록 및 수정

import React, { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { Form, Input, Button, Upload, Card } from 'antd';
import { UploadOutlined } from '@ant-design/icons';
import { createAdRequest, updateAdRequest } from '../../reducers/ad/adReducer';

const AdForm = ({ isEditing, currentAd, setIsEditing }) => {
  const [form] = Form.useForm();
  const [fileList, setFileList] = useState([]);
  const dispatch = useDispatch();

  // 수정 모드일 때 폼 데이터 채우기
  useEffect(() => {
    if (isEditing && currentAd) {
      form.setFieldsValue({
        title: currentAd.title,
        content: currentAd.content,
      });
    } else {
      form.resetFields();
      setFileList([]);
    }
  }, [isEditing, currentAd, form]);

  const onFinish = (values) => {
    const dto = { title: values.title, content: values.content };
    const file = fileList[0]?.originFileObj;

    if (isEditing) {
      dispatch(updateAdRequest({ adId: currentAd.id, dto, file }));
      setIsEditing(false);
    } else {
      dispatch(createAdRequest({ dto, file }));
    }
    form.resetFields();
    setFileList([]);
  };

  return (
    <Card title={isEditing ? "광고 수정하기" : "새 광고 등록"}>
      <Form form={form} layout="vertical" onFinish={onFinish}>
        <Form.Item name="title" label="제목" rules={[{ required: true }]}>
          <Input placeholder="광고 제목을 입력하세요" />
        </Form.Item>
        <Form.Item name="content" label="내용" rules={[{ required: true }]}>
          <Input.TextArea rows={4} placeholder="광고 내용을 입력하세요" />
        </Form.Item>
        <Form.Item label="이미지 첨부">
          <Upload
            beforeUpload={() => false}
            fileList={fileList}
            onChange={({ fileList }) => setFileList(fileList)}
            maxCount={1}
          >
            <Button icon={<UploadOutlined />}>파일 선택</Button>
          </Upload>
        </Form.Item>
        <Button type="primary" htmlType="submit" block>
          {isEditing ? "수정 완료" : "등록하기"}
        </Button>
        {isEditing && (
          <Button onClick={() => setIsEditing(false)} block style={{ marginTop: 8 }}>
            취소
          </Button>
        )}
      </Form>
    </Card>
  );
};

export default AdForm;