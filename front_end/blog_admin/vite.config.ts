import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import { resolve } from "path";
import PurgeIcons from 'vite-plugin-purge-icons'

function pathResolve(dir: string) {
  return resolve(process.cwd(), ".", dir);
}

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    PurgeIcons(),
  ],
  resolve: {
    alias: [
      {
        find: /\/@\//,
        replacement: pathResolve("src") + "/",
      }
    ],
  },
  server: {
    proxy: {
      "/api/login": {
        target: "http://localhost:8888",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
      "/api/admin": {
        target: "http://localhost:8888",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
      "/api": {
        target: "http://localhost:8889",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      }
    },
  },
});
