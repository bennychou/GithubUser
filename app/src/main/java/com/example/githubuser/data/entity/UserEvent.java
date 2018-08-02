package com.example.githubuser.data.entity;

import com.google.gson.annotations.SerializedName;

public class UserEvent {
//    {
//        "id": "8055639113",
//        "type": "PushEvent",
//        "actor": {
//            "id": 1236670,
//            "login": "bennychou",
//            "display_login": "bennychou",
//            "gravatar_id": "",
//            "url": "https://api.github.com/users/bennychou",
//            "avatar_url": "https://avatars.githubusercontent.com/u/1236670?"
//        },
//        "repo": {
//            "id": 142970174,
//            "name": "bennychou/GithubUser",
//            "url": "https://api.github.com/repos/bennychou/GithubUser"
//        },
//        "payload": {
//            "push_id": 2766912199,
//            "size": 1,
//            "distinct_size": 1,
//            "ref": "refs/heads/master",
//            "head": "63eb1908c5cdae7cf4209980da810f6576b03917",
//            "before": "b3ff7cdf794cf9156a4d8f9038f108aafdb8a803",
//            "commits": [
//                {
//                    "sha": "63eb1908c5cdae7cf4209980da810f6576b03917",
//                    "author": {
//                        "email": "bennychou7573@hotmail.com",
//                        "name": "bennychou"
//                    },
//                    "message": "Add swipe refresh",
//                    "distinct": true,
//                    "url": "https://api.github.com/repos/bennychou/GithubUser/commits/63eb1908c5cdae7cf4209980da810f6576b03917"
//                }
//            ]
//         },
//         "public": true,
//         "created_at": "2018-08-02T09:55:27Z"
//    }

    private String id;
    private String type;
    private Actor actor;
    private Repo repo;
    private Payload payload;
    @SerializedName("public")
    private boolean isPublic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public class Actor {
        private String id;
        private String login;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Repo {
        private String id;
        private String name;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Payload {
        @SerializedName("push_id")
        private long pushId;
        private String ref;
        private String head;
        private String before;

        public long getPushId() {
            return pushId;
        }

        public void setPushId(long pushId) {
            this.pushId = pushId;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }
    }
}
