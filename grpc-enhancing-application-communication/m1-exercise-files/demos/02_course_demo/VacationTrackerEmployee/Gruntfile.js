module.exports = function (grunt) {


	grunt.initConfig({
		sass: {
			options: {

			},
			dist: {
				files: {
					'public/css/site.css': 'public/css/site.scss'
				}
			}
		},

		watch: {
			sass: {
				files: ['public/css/*.scss'],
				tasks: ['sass'],
				options: {
					spawn: false
				}
			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-sass');

	grunt.registerTask('default', ['watch']);
};