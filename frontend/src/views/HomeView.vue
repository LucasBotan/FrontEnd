<template>
  <section class="home">
    <div class="image-container">
      <h1>Com sua doação, você nos ajuda a mudar a realidade de milhares de animais</h1>
    </div>
    <div class="form-container">
      <div class="form-container-header">
        <h2>Preencha o formulário e seja um doador!</h2>
      </div>
      <div class="form-container-body">
        <form>
          <label for="name">
            Nome*:
            <input type="text" name="name" placeholder="Digite seu nome">
          </label>
          <label for="email">
            E-mail*:
            <input type="email" name="email" placeholder="Digite seu e-mail">
          </label>
          <label for="password">
            Senha*:
            <input type="password" name="password" placeholder="Digite sua senha">
          </label>
          <label for="nasc">
            Data de nascimento*:
            <input type="text" name="nasc" placeholder="DD/MM/AAAA">
          </label>
          <label for="sexo">
            Sexo*:
            <select name="sexo">
              <option value="M">Masculino</option>
              <option value="F">Feminino</option>
            </select>
          </label>
          <label for="tel">
            Telefone*:
            <input type="tel" name="tel" placeholder="Digite seu telefone">
          </label>
        </form>
        <label for="policies" class="policies">
          <input type="checkbox" name="policies" />
          Li e aceito as políticas de privacidade.
        </label>
        <button>QUERO DOAR!</button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'HomeView',
  components: {},
};
</script>

<style scoped lang="scss">
.home {
  width: 100vw;
  min-height: 100vh;
  display: flex;
  .image-container {
    display: flex;
    align-items: flex-end;
    background-image: url('../assets/bg_home.png');
    background-size: cover;
    background-position: center;
    width: 75vw;
    padding: 35px 55px;
    h1 {
      font-size: 68px;
      margin: 0;
      color: #FFF;
    }
  }
  .form-container {
    width: 25vw;
    .form-container-header {
      display: flex;
      align-items: center;
      background: #E38024;
      padding: 20px 10px;
      h2 {
        color: #FFF;
        font-size: 20px;
        margin: 0;
        text-align: center;
        width: 100%;
      }
    }
    .form-container-body {
      padding: 25px;
      form {
        display: flex;
        flex-direction: column;
        gap: 15px;
        label {
          display: flex;
          flex-direction: column;
          gap: 8px;
          input[type='text'],
          input[type='email'],
          input[type='password'],
          input[type='tel'],
          select {
            outline: none;
            border: 1px solid red;
            background: #FFF;
            border: 1px solid rgba(0, 0, 0, 0.3);
            border-radius: 5px;
            height: 30px;
            padding: 2px 10px;
          }
        }
      }
      .policies {
        display: block;
        margin-top: 30px;
      }
      button {
        width: 100%;
        height: 40px;
        background-color: #B9151B;
        border-radius: 8px;
        outline: none;
        border: 0;
        color: #FFF;
        margin-top: 30px;
        transition: .3s;
        cursor: pointer;
        &:hover {
          opacity: .85;
        }
      }
    }
  }
}
</style>
