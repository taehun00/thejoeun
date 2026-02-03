// utils/fileUrl.js
export function fileUrl(path) {
  const base = process.env.NEXT_PUBLIC_BACKEND_URL || "http://localhost:8484";
  if (!path) return "";

  // 이미 절대경로면 그대로
  if (typeof path === "string" && (path.startsWith("http://") || path.startsWith("https://"))) {
    return path;
  }

  // path가 "/upload/xxx" 형태면 그대로 붙임
  if (path.startsWith("/")) return `${base}${path}`;

  // DB에 "reviewimg/xxx.jpg" 같은 형태 저장된 값이라고 가정
  return `${base}/upload/${path}`;
}
