import Vue from "vue";
import VueI18n from "vue-i18n";

Vue.use(VueI18n);

function loadLocaleMessages() {
  const language = require.context("./lang", true, /[A-Za-z0-9-_,\s]+\.json$/i);
  const messages = {};
  language.keys().forEach((key) => {
    const matched = key.match(/([A-Za-z0-9-_]+)\./i);
    if (matched && matched.length > 1) {
      const locale = matched[1];
      messages[locale] = language(key);
    }
  });
  return messages;
}

export default new VueI18n({
  locale: process.env.VUE_APP_I18N_LOCALE || "zh",
  fallbackLocale: process.env.VUE_APP_I18N_FALLBACK_LOCALE || "zh",
  messages: loadLocaleMessages(),
});
