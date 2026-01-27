// components/reviewboard/ReviewTableColumns.js
export function ratingToStar(rating) {
  if (rating === 5) return "★★★★★";
  if (rating === 4) return "★★★★☆";
  if (rating === 3) return "★★★☆☆";
  if (rating === 2) return "★★☆☆☆";
  if (rating === 1) return "★☆☆☆☆";
  return "-";
}

export default function ReviewTableColumns({ total, pageNo, pageSize = 10 }) {
  return [
    {
      title: "NO",
      key: "no",
      width: 70,
      align: "center",
      render: (_, record, idx) => total - ((pageNo - 1) * pageSize + idx),
    },
    {
      title: "브랜드",
      dataIndex: "brandname",
      key: "brandname",
      width: 120,
      align: "center",
      ellipsis: true,
    },
    {
      title: "제품명",
      dataIndex: "foodname",
      key: "foodname",
      width: 200,
      align: "center",
      ellipsis: true,
      onCell: () => ({ style: { whiteSpace: "nowrap" } }),
    },
    {
      title: "평점",
      dataIndex: "rating",
      key: "rating",
      width: 120,
      align: "center",
      render: (rating) => <span style={{ fontWeight: 700 }}>{ratingToStar(Number(rating))}</span>,
    },
    {
      title: "제목",
      dataIndex: "title",
      key: "title",
      align: "center",
      ellipsis: true,
    },
    {
      title: "작성자",
      dataIndex: "nickname",
      key: "nickname",
      width: 120,
      align: "center",
      ellipsis: true,
    },

  ];
}
