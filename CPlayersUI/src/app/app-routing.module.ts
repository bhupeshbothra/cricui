import { DetailsComponent } from './modules/cricplayer/components/details/details/details.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './modules/authentication/components/login/login.component';
import { RegisterComponent } from './modules/authentication/components/register/register.component';
import { FavoriteComponent } from './modules/cricplayer/components/favorite/favorite.component';
import { RecommendedComponent } from './modules/cricplayer/components/recommended/recommended.component';
import { CardContainerComponent } from './modules/cricplayer/components/card-container/card-container.component';
import { AuthGuardService } from './modules/cricplayer/auth-guard.service';

const routes: Routes = [

  {
    path: "",
    component: LoginComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "Player",
    component: CardContainerComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "Favorite",
    component: FavoriteComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: "RecommendedPlayer",
    component: RecommendedComponent
  },
  {
    path: "details",
    component: DetailsComponent
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
