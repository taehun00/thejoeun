// components/ad/AdItem.js
// 광고 개별 항목

import React from 'react';
import { Card, Button, Typography } from 'antd';
const { Paragraph } = Typography;

const AdItem = ({ ad, onEdit, onDelete }) => {
  return (
    <Card 
      style={{ marginBottom: 16 }}
      actions={[
        <Button type="link" onClick={() => onEdit(ad.id)}>수정</Button>,
        <Button type="link" danger onClick={() => onDelete(ad.id)}>삭제</Button>
      ]}
    >
      <Card.Meta 
        title={ad.title} 
        description={`등록일: ${new Date(ad.createdAt).toLocaleDateString()}`} 
      />
      <div style={{ marginTop: 15 }}>
        <Paragraph ellipsis={{ rows: 2 }}>{ad.content}</Paragraph>
      </div>
      {ad.imageUrl && (
        <img src={ad.imageUrl} alt="광고 이미지" style={{ width: '100%', maxHeight: 200, objectFit: 'cover' }} />
      )}
    </Card>
  );
};

export default AdItem;