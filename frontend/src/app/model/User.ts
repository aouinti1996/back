import {Role} from "./Enums/Role";

export class User{
  login !: string;
  email !: string;
  password !: string;
  role !: Role;
}
