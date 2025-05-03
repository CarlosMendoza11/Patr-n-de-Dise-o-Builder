const ThemeFactory = {
    applyTheme: function (theme) {
      const app = document.getElementById("app");
      app.className = ""; // reset classes
      app.classList.add(theme + "-theme");
    }
  };
  