import {Picture} from "./picture";

export interface User {
  id: number
  last_name: string
  first_name: string
  middle_name: string
  username: string
  password: string
  count: number
  picture: Picture
}

export interface UserLogin {
  username: string
  password: string
}

export interface UserCreate {
  pictureId: number;
  last_name: string
  first_name: string
  middle_name: string
  username: string
  password: string
}

export interface UserUpdate {
  pictureId: number;
  last_name: string
  first_name: string
  middle_name: string
  username: string
  password: string
  name: string
}
