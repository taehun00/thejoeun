import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Table, Button, Select } from "antd";
import {
  fetchReportsRequest,
  handleReportRequest,
} from "../../../reducers/admin/reportReducer";

export default function AdminReportPage() {
  const dispatch = useDispatch();
  const { reports, loading } = useSelector((state) => state.adminReport);

  useEffect(() => {
    dispatch(
      fetchReportsRequest({
        type: "ALL",
        page: 0,
        size: 10,
      })
    );
  }, [dispatch]);

  const handleAction = (reportId, action) => {
    dispatch(
      handleReportRequest({
        reportId,
        status: "RESOLVED",
        action,
        note: "관리자 처리",
      })
    );
  };

  const columns = [
    { title: "ID", dataIndex: "reportId" },
    { title: "타입", dataIndex: "targetType" },
    { title: "사유", dataIndex: "reason" },
    {
      title: "처리",
      render: (_, record) => (
        <>
          <Button danger onClick={() => handleAction(record.reportId, "DELETE")}>
            삭제
          </Button>
          <Button onClick={() => handleAction(record.reportId, "IGNORE")}>
            무시
          </Button>
        </>
      ),
    },
  ];

  return (
    <Table
      rowKey="reportId"
      loading={loading}
      columns={columns}
      dataSource={reports}
    />
  );
}
