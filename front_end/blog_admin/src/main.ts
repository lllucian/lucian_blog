import { createApp } from 'vue'
import App from './App.vue'
import { router } from '/@/router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import axios from 'axios'
import VueAxios from 'vue-axios'
import { stroage } from '/@/stroage'
import TEditor from '/@/components/form/tinymce/TEditor.vue'
import '@purge-icons/generated'
import BootstrapVue3 from 'bootstrap-vue-3'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'

const app = createApp(App)

app.component('TEditor',TEditor)

app.use(router)

app.use(stroage)

app.use(VueAxios, axios)

app.use(BootstrapVue3)

app.use(ElementPlus, {
    locale: zhCn,
})

app.mount('#app')
