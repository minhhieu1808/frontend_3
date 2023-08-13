package org.example;

public class Category {

        private Integer id;
        private String name;
        private String slug;

        public Integer getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public String getSlug() {
                return slug;
        }

        public Category(Integer id, String name, String slug) {
                this.id = id;
                this.name = name;
                this.slug = slug;
        }
}
