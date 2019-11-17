import {Id} from '../models/id'

export class Book extends Id{
  header: string;
  authors:string;
  overview: string; // maybe Text?
  photo:string;
  file:number;
  status: string;
  genre:string;
}

