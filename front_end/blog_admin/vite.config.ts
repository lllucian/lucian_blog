import {defineConfig, loadEnv} from "vite";
import vue from "@vitejs/plugin-vue";
import {resolve} from "path";
import PurgeIcons from 'vite-plugin-purge-icons'

function pathResolve(dir: string) {
    return resolve(process.cwd(), ".", dir);
}

// https://vitejs.dev/config/
export default defineConfig(({mode, command}) => {
    const env = loadEnv(mode, process.cwd())
    const { VITE_APP_ENV } = env
    return {
        base: VITE_APP_ENV === 'production' ? '/' : '/',
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
            host: true,
            proxy: {
                "/lucian_dev/admin/login": {
                    target: "http://localhost:8888",
                    changeOrigin: true,
                    rewrite: (path) => path.replace(/^\/lucian_dev/, ""),
                },
                "/lucian_dev/admin": {
                    target: "http://localhost:8888",
                    changeOrigin: true,
                    rewrite: (path) => path.replace(/^\/lucian_dev/, ""),
                },
                "/lucian_dev": {
                    target: "http://localhost:8889",
                    changeOrigin: true,
                    rewrite: (path) => path.replace(/^\/lucian_dev/, ""),
                },
                "/files":{
                    target: "http://localhost:9000",
                    changeOrigin: true,
                    rewrite: (path) => path.replace(/^\/files/, ""),
                }
            },
        },
        build: {
            chunkSizeWarningLimit: 1500,
            rollupOptions: {
                output: {
                    manualChunks(id) {
                        if (id.includes('node_modules')) {
                            return id.toString().split('node_modules/')[1].split('/')[0].toString();
                        }
                    }
                }
            }
        }
    }
});
