using System;
using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using System.Net;
using System.IO;
using System.Text;
using System.Threading;
using Java.Lang;

namespace ServerMonitor
{
	// Icons from - http://www.iconarchive.com/show/chaninja-icons-by-chaninja/Folder-Icons-icon.html
	[Activity (Label = "ServerMonitor", MainLauncher = true)]
	public class Activity1 : Activity
	{
		protected Button _checkButton;
		protected ProgressBar _progress;
		protected Spinner _spinner;

		protected override void OnCreate (Bundle bundle)
		{
			base.OnCreate (bundle);

			// Set our view from the "main" layout resource
			SetContentView (Resource.Layout.Main);

			// Get our button from the layout resource, and attach an event to it
			_checkButton = FindViewById<Button> (Resource.Id.myButton);
			_progress = FindViewById<ProgressBar> (Resource.Id.progressBar1);
			_spinner = FindViewById<Spinner> (Resource.Id.spinner1);

			_progress.Visibility = ViewStates.Invisible;

			var sites = new string[] { "triadLeadGen", "compareTopSchools9", "topLaw2", "accounting3", "compareTopSchools1" };

			var adp = new ArrayAdapter<System.String>(this, Android.Resource.Layout.SimpleDropDownItem1Line, sites);

			_spinner.Adapter = adp;

			_checkButton.Click += delegate {
				_checkButton.Text = "Stop Checking";
				_progress.Visibility = ViewStates.Visible;

				var httpReq = (HttpWebRequest)HttpWebRequest.Create (new Uri (FetchUrl((string)_spinner.SelectedItem)));
				httpReq.BeginGetResponse (new AsyncCallback (ResponseCallback), httpReq);
			};
		}

		private string FetchUrl(string item) {
			if (item == "triadLeadGen") return "http://partners.triadleadgen.com/partners/eduleadconsumer.aspx";
			if (item == "compareTopSchools9") return "http://www.comparetopschools.com/landing5/default9.aspx";
			if (item == "topLaw2") return "http://www.toplawenforcementschools.com/law-enforcement-degrees/default2.aspx";
			if (item == "accounting3") return "http://www.comparetopschools.com/accounting/default3.aspx";
			if (item == "compareTopSchools1") return "http://www.comparetopschools.com/landing5/default1.aspx";

			return "http://www.google.com";
		}

		private string FetchPattern(string item) {
			if (item == "triadLeadGen") return "School Code Was Missing";
			if (item == "compareTopSchools9") return "UA-17717128-1";
			if (item == "topLaw2") return "UA-17717128-1";
			if (item == "accounting3") return "UA-17717128-1";
			if (item == "compareTopSchools1") return "UA-17717128-1";

			return "ZZZXXXYYY((()))---***";
		}

		private void ResponseCallback (IAsyncResult ar)
		{
			var httpReq = (HttpWebRequest)ar.AsyncState;
			
			using (var httpRes = (HttpWebResponse)httpReq.EndGetResponse (ar)) {
				var stream = httpRes.GetResponseStream();

				Encoding encode = System.Text.Encoding.GetEncoding("utf-8");

				StreamReader readStream = new StreamReader(stream, encode);

				var responseText = readStream.ReadToEnd();
				var message = "";

				readStream.Close();

				RunOnUiThread (() => {
					if (responseText.Contains(FetchPattern((string)_spinner.SelectedItem))) {
						message = "Success!";
					} else {
						message = "Fail";
					}

					SlowMethod (message);
				});
			}
		}

		private void SlowMethod(string message) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this).SetTitle("Fetch Results").SetCancelable(true).SetMessage(message);
			
			builder.Show();
			
			_checkButton.Text = "Check Servers";
			_progress.Visibility = ViewStates.Invisible;
		}
	}
}