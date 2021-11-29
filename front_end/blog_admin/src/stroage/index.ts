import { createStore } from "vuex";

export const stroage = createStore({
  state: () => ({
    // token
    token: undefined,
  }),
  getters: {
    getToken: (state) => {
      return state.token || localStorage.token;
    },
  },
  mutations: {
    setToken: (state, payload) => {
      state.token = payload.token;
      localStorage.token = payload.token;
    }, 
    clearToken: (state) => {
      state.token = undefined;
      localStorage.removeItem('token')
    },
    checkToken: (state) => {
      if (state.token) localStorage.token = state.token;
      if (localStorage.token && !state.token) state.token = localStorage.token;
    }
  }
});
