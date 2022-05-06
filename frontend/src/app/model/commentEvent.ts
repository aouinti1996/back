import {User} from "./User";
import {Event} from "./Event";

export class CommentEvent {
  idCommentE !: number;
  content !: string;
  createDate !: Date;
  event !: Event;
  user !: User
}
