import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavComponent } from "../view/nav/nav.component";
import { FooterComponent } from "../view/footer/footer.component";
import { LoginComponent } from "../view/login/login.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, NavComponent, FooterComponent, LoginComponent]
})
export class AppComponent {
  title = 'findfood';
}
