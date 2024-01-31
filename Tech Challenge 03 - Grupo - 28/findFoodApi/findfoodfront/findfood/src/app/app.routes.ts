import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { NavComponent } from '../view/nav/nav.component';
import { FooterComponent } from '../view/footer/footer.component';
//import { CadastrarAvaliacaoComponent } from '../view/cadastro/avaliacao/avaliacao.component';
//import { CadastrarRestauranteComponent } from '../view/cadastro/cadastrar-restaurante/cadastrar-restaurante.component';
//import { CadastrarUsuarioComponent } from '../view/cadastro/cadastrar-usuario/cadastrar-usuario.component';
//import { GerenciarReservaComponent } from '../view/gerenciar-reserva/gerenciar-reserva.component';

export const routes: Routes = [
  // Estrutura Principal
  { path: '', redirectTo: 'AppComponent', pathMatch: 'full' },
  { path: 'AppComponent', component: AppComponent },
  { path: 'NavComponent', component: NavComponent },
  { path: 'FooterComponent', component: FooterComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
