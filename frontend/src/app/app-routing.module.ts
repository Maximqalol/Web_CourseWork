import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginPageComponent} from "./login-page/login-page.component";
import {RegisterPageComponent} from "./register-page/register-page.component";
import {EditPageComponent} from "./edit-page/edit-page.component";
import {MainPageComponent} from "./main-page/main-page.component";

const routes: Routes = [
      {path: '', redirectTo: "/login", pathMatch: "full"},
      {path: 'login', component: LoginPageComponent},
      {path: 'register', component: RegisterPageComponent},
      //{path: '', redirectTo: "/mainpage", pathMatch: "full"},
      {path: 'mainpage', component: MainPageComponent},
      {path: 'edit', component: EditPageComponent},
      {path: 'logout', redirectTo: "/login"}
]

@NgModule({
  imports:[
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
