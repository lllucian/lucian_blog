import { createApp } from 'vue'
import App from './App.vue'
import { router } from '/@/router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import axios from 'axios'
import VueAxios from 'vue-axios'

const app = createApp(App)

app.use(router)

app.use(VueAxios, axios)

app.use(ElementPlus, {
    locale: zhCn,
})

app.mount('#app')
