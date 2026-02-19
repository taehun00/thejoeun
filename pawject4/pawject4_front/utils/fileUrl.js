export function fileUrl(path) {
  if (!path) return "";

  // 절대경로면 그대로
  if (
    typeof path === "string" &&
    (path.startsWith("http://") || path.startsWith("https://"))
  ) {
    return path;
  }

  const API_BASE = "http://localhost:8484";

  // 이미 /uploads 포함된 경우
  if (path.startsWith("/uploads/")) {
    return `${API_BASE}${path}`;
  }

  // DB에 exec/xxx.jpg 같은 형태로 저장된 경우
  return `${API_BASE}/uploads/${path}`;
}
