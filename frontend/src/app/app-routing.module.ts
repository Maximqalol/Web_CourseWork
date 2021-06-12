import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginPageComponent} from "./login-page/login-page.component";
import {AuthLayoutComponent} from "./shared/layouts/auth-layout/auth-layout.component";
import {SiteLayoutComponent} from "./shared/layouts/site-layout/site-layout.component";
import {RegisterPageComponent} from "./register-page/register-page.component";
import {EditPageComponent} from "./edit-page/edit-page.component";
import {MainPageComponent} from "./main-page/main-page.component";
import {AuthGuard} from "./shared/guard/auth.guard";

const routes: Routes = [
  {
    path: '', component: AuthLayoutComponent, children: [
      {path: '', redirectTo: "/login", pathMatch: "full"},
      {path: 'login', component: LoginPageComponent},
      {path: 'register', component: RegisterPageComponent}
    ]
  },
  {
    path: '', component: SiteLayoutComponent, children: [
      //{path: '', redirectTo: "/mainpage", pathMatch: "full"},
      {path: 'mainpage', component: MainPageComponent},
      {path: 'edit', component: EditPageComponent},
      {path: 'logout', redirectTo: "/login"}
    ]
  }
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
