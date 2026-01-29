// components/tester/TesterToggleTable.js
import { Table } from "antd";

/**
 *  Tester 전용 
 * - expand 아이콘 제거
 * - 테스터 리스트 UI에 맞춘 기본 scroll/tableLayout 포함
 *
 * props
 * - rowKey
 * - columns
 * - dataSource
 * - loading
 * - pageNo, total, pageSize, onChangePage
 * - expandedRowRender
 * - expandedRowKeys
 * - onExpand
 */

export default function TesterToggleTable({
  rowKey = "testerid",
  columns,
  dataSource,
  loading,

  pageNo,
  total,
  pageSize = 20,
  onChangePage,

  expandedRowRender,
  expandedRowKeys,
  onExpand,
}) {
  return (
    <Table
      rowKey={rowKey}
      columns={columns}
      dataSource={dataSource}
      loading={loading}
      tableLayout="fixed"
      scroll={{ x: 900 }}
      expandable={{
        expandedRowRender,
        rowExpandable: () => true,

        expandedRowKeys,
        onExpand,

        expandRowByClick: true, //  행 클릭으로 열고닫기
        expandIcon: () => null, // 아이콘 제거
        expandColumnWidth: 0,   // 여백 제거
      }}
      pagination={{
        current: pageNo,
        total,
        pageSize,
        showSizeChanger: false,
        onChange: onChangePage,
        position: ["bottomCenter"],
      }}
    />
  );
}
