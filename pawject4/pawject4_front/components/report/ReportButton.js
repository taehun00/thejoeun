import { useState } from "react";
import { Modal, Select, Input, Button } from "antd";
import { useDispatch } from "react-redux";
import { reportRequest } from "../../reducers/report/reportReducer";
import { WarningOutlined } from "@ant-design/icons";

const { TextArea } = Input;

export default function ReportButton({ targetType, targetId }) {
  const dispatch = useDispatch();
  const [open, setOpen] = useState(false);
  const [reason, setReason] = useState("");
  const [details, setDetails] = useState("");

  const submit = () => {
    dispatch(
      reportRequest({
        targetType,
        targetId,
        reason,
        details,
      })
    );
    setOpen(false);
  };

  return (
    <>
      <WarningOutlined
        style={{ color: "red", cursor: "pointer" }}
        onClick={() => setOpen(true)}
      />

      <Modal
        title="신고하기"
        open={open}
        onOk={submit}
        onCancel={() => setOpen(false)}
      >
        <Select
          style={{ width: "100%", marginBottom: 10 }}
          placeholder="신고 사유"
          onChange={setReason}
        >
          <Select.Option value="욕설">욕설</Select.Option>
          <Select.Option value="음란물">음란물</Select.Option>
          <Select.Option value="광고">광고</Select.Option>
          <Select.Option value="기타">기타</Select.Option>
        </Select>

        <TextArea
          rows={3}
          placeholder="상세 사유 (선택)"
          value={details}
          onChange={(e) => setDetails(e.target.value)}
        />
      </Modal>
    </>
  );
}
