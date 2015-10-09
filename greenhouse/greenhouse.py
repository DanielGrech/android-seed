import os
import sys
import inspect

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))) + '/garden') 

from flask import Flask, request, Response, send_file
import grow
import json
import random
import shutil

application = Flask(__name__)

@application.route("/")
def index():
	return application.send_static_file('index.html')

@application.route("/about")
def about():
	return application.send_static_file('about.html')

@application.route('/grow', methods=['POST'])
def generate():
	output_dir = "/tmp/" + str(random.randint(1, sys.maxint))
	output_file_name = "/tmp/" + str(random.randint(1, sys.maxint))
	try:
		data = {
				"app_name" : request.form["app_name"],
				"language" : request.form["language"],
				"company_name" : request.form["company_name"],
				"app_package_name_prefix" : request.form["app_package_name_prefix"],
				"app_class_prefix": request.form["app_class_prefix"],
				"application_id" : request.form["application_id"],
				"compile_sdk_version" : int(request.form["compile_sdk_version"]),
				"build_tools_version" : request.form["build_tools_version"],
				"min_sdk_version" : int(request.form["min_sdk_version"]),
				"target_sdk_version" : int(request.form["target_sdk_version"]),
				"crashlytics_api_key" : request.form["crashlytics_api_key"],
				"output_dir" : output_dir
		}

		grow.grow(data)

		shutil.make_archive(output_file_name, 'zip', output_dir)

		return send_file(output_file_name + ".zip", 
			mimetype="application/zip",
			as_attachment=True,
			attachment_filename=request.form["app_class_prefix"] + ".zip")
	finally:
		if os.path.exists(output_dir):
			shutil.rmtree(output_dir)
		if os.path.exists(output_file_name):
			os.remove(output_file_name)

if __name__ == "__main__":
    application.run(host='0.0.0.0')