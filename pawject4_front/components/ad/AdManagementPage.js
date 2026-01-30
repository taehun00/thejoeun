// components/ad/AdManagementPage.js
// 광고 통합관리

import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { deleteAdRequest, fetchAdRequest } from '../../reducers/ad/adReducer';
import AdForm from './AdForm';
import AdItem from './AdItem';
import { Row, Col, Divider } from 'antd';

const AdManagementPage = () => {
  const dispatch = useDispatch();
  const { ads, currentAd } = useSelector((state) => state.ad);
  const [isEditing, setIsEditing] = useState(false);

  const handleEdit = (adId) => {
    dispatch(fetchAdRequest({ adId }));
    setIsEditing(true);
  };

  const handleDelete = (adId) => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      dispatch(deleteAdRequest({ adId }));
    }
  };

  return (
    <div style={{ padding: '24px' }}>
      <h1>광고 관리 시스템</h1>
      <Row gutter={24}>
        <Col span={8}>
          <AdForm 
            isEditing={isEditing} 
            currentAd={currentAd} 
            setIsEditing={setIsEditing} 
          />
        </Col>
        <Col span={16}>
          <h2>광고 목록</h2>
          <Divider />
          {ads.length > 0 ? (
            ads.map(ad => (
              <AdItem 
                key={ad.id} 
                ad={ad} 
                onEdit={handleEdit} 
                onDelete={handleDelete} 
              />
            ))
          ) : (
            <p>등록된 광고가 없습니다.</p>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default AdManagementPage;
